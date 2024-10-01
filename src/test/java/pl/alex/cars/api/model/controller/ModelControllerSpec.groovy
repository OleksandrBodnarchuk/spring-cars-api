package pl.alex.cars.api.model.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.alex.cars.api.model.dto.ModelResponse
import pl.alex.cars.api.model.service.ModelService
import spock.lang.Specification
import spock.lang.Title

import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ModelController)
@Title("ModelController Specification")
class ModelControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    ModelService modelService

    static final String TOYOTA = "TOYOTA"
    List<ModelResponse> modelResponses
    Page<ModelResponse> pageResponse

    def setup() {
        // Initializing test data
        modelResponses = [
                new ModelResponse(1L, "Avensis"),
                new ModelResponse(2L, "Hilux"),
                new ModelResponse(3L, "Corolla")
        ]
        pageResponse = new PageImpl<>(modelResponses, PageRequest.of(0, 10), modelResponses.size())
    }

    def "GET /v0/models/{brand} should return models DTO matched by brand name"() {
        given: "A pageable response from the service"
        given(modelService.getModelResponseByBrandName(any(Pageable))).willReturn(pageResponse)

        expect: "MockMvc performs GET request and returns a paginated list of models"
        mockMvc.perform(get("/v0/models/{brand}", TOYOTA)
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.content').isArray())
                .andExpect(jsonPath('$.numberOfElements').value(modelResponses.size()))
                .andExpect(jsonPath('$.content[0].name').value("Avensis"))
                .andExpect(jsonPath('$.content[1].name').value("Hilux"))
                .andExpect(jsonPath('$.content[2].name').value("Corolla"))
    }

    def "GET /v0/models/{brand} should return empty list if no models are found"() {
        given: "An empty pageable response from the service"
        given(modelService.getModelResponseByBrandName(any(Pageable))).willReturn(Page.empty())

        expect: "MockMvc performs GET request and returns an empty list"
        mockMvc.perform(get("/v0/models/{brand}", "UNKNOWN_BRAND")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.content').isArray())
                .andExpect(jsonPath('$.numberOfElements').value(0))
    }

    def "GET /v0/models/{brand} should return bad request for invalid parameters"() {
        expect: "MockMvc performs GET request with invalid parameters and returns a Bad Request status"
        mockMvc.perform(get("/v0/models/{brand}", TOYOTA)
                .param("page", "-1")
                .param("size", "-10")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
    }

    def "GET /v0/models/{name}/modifications should return redirect for model modifications"() {
        expect: "MockMvc performs GET request and returns a 301 redirect to the modifications URL"
        mockMvc.perform(get("/v0/models/{name}/modifications", "Corolla")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string(HttpHeaders.LOCATION, "/submodels/Corolla"))
    }
}
