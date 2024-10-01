package pl.alex.cars.api.brand.ui

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.alex.cars.api.TestEntityFactory
import pl.alex.cars.api.brand.data.Brand
import pl.alex.cars.api.brand.exception.BrandNotFoundException
import pl.alex.cars.api.model.dto.ModelResponse
import spock.lang.Specification
import spock.lang.Title

import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BrandController)
@Title("BrandController Specification")
class BrandControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    BrandService brandService

    @Autowired
    ObjectMapper mapper

    static final String API_VERSION = "/v0"
    static final List<Brand> BRANDS = TestEntityFactory.createBrandsWithModels(5, 3)
    static final Brand brand1 = BRANDS.get(0)
    static final Brand brand2 = BRANDS.get(1)

    static final BrandResponse brand1Response = BrandResponse.of(brand1.getId(), brand1.getName(),
            brand1.getModels().stream()
                    .map(model -> new ModelResponse(model.getId(), model.getName()))
                    .toList())

    static final BrandResponse brand2Response = BrandResponse.of(brand2.getId(), brand2.getName(),
            brand2.getModels().stream()
                    .map(model -> new ModelResponse(model.getId(), model.getName()))
                    .toList())

    def "GET /brands/multiple should return multiple brands matched by their names"() {
        given: "A brand request with valid names"
        BrandRequest request = new BrandRequest(names: [brand1.getName(), brand2.getName()])
        given(brandService.getMultipleBrands(any(BrandRequest))).willReturn([brand1Response, brand2Response])

        expect: "MockMvc performs POST request and returns a list of brands"
        mockMvc.perform(post(API_VERSION + "/brands/multiple")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value(brand1.getName()))
                .andExpect(jsonPath('$[1].name').value(brand2.getName()))
    }

    def "GET /brands/multiple should fail validation when name list is empty"() {
        given: "An empty brand request"
        BrandRequest request = new BrandRequest(names: [])

        expect: "MockMvc returns Bad Request due to validation failure"
        mockMvc.perform(post(API_VERSION + "/brands/multiple")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
    }

    def "GET /brands/{name} should return one brand by name with models"() {
        given: "A valid brand name"
        given(brandService.getBrandResponseByName(brand1.getName(), true)).willReturn(brand1Response)

        expect: "MockMvc performs GET request and returns the brand with models"
        mockMvc.perform(get(API_VERSION + "/brands/{name}", brand1.getName())
                .param("includeModels", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.name').value(brand1.getName()))
                .andExpect(jsonPath('$.models').isArray())
                .andExpect(jsonPath('$.models[0].name').value(brand1.getModels().get(0).getName()))
    }

    def "GET /brands/{name} should return one brand by name without models"() {
        given: "A valid brand name and response without models"
        BrandResponse brandResponse = BrandResponse.of(brand1.getId(), brand1.getName(), [])
        given(brandService.getBrandResponseByName(brand1.getName(), false)).willReturn(brandResponse)

        expect: "MockMvc performs GET request and returns the brand without models"
        mockMvc.perform(get(API_VERSION + "/brands/{name}", brand1.getName())
                .param("includeModels", "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.name').value(brand1.getName()))
                .andExpect(jsonPath('$.models').isArray())
                .andExpect(jsonPath('$.models.length()').value(0))
    }

    def "GET /brands should return a list of brands"() {
        given: "The brand service returns a list of brands"
        given(brandService.findAllBrands()).willReturn([brand1Response, brand2Response])

        expect: "MockMvc performs GET request and returns the list of brands"
        mockMvc.perform(get(API_VERSION + "/brands")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$').isArray())
                .andExpect(jsonPath('$.length()').value(2))
                .andExpect(jsonPath('$[0].name').value(brand1.getName()))
                .andExpect(jsonPath('$[1].name').value(brand2.getName()))
    }

    def "GET /brands should return an empty list when no brands are found"() {
        given: "The brand service returns an empty list"
        given(brandService.findAllBrands()).willReturn([])

        expect: "MockMvc performs GET request and returns an empty list"
        mockMvc.perform(get(API_VERSION + "/brands")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$').isArray())
                .andExpect(jsonPath('$.length()').value(0))
    }

    def "GET /brands/{name} should return 404 when brand is not found"() {
        given: "An invalid brand name"
        given(brandService.getBrandResponseByName("UNKNOWN", true)).willThrow(new BrandNotFoundException("Brand not found"))

        expect: "MockMvc performs GET request and returns 404 with error message"
        mockMvc.perform(get(API_VERSION + "/brands/{name}", "UNKNOWN")
                .param("includeModels", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath('$.message').value("Brand not found"))
    }
}
