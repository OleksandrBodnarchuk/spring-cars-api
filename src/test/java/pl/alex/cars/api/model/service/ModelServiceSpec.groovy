package pl.alex.cars.api.model.service


import pl.alex.cars.api.model.dto.ModelResponse
import pl.alex.cars.api.brand.data.Brand
import pl.alex.cars.api.model.entity.Model
import pl.alex.cars.api.model.repository.ModelRepository
import pl.alex.cars.api.model.dto.ModelRequestQuery
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import spock.lang.Specification
import spock.lang.Title

@Title("ModelService Test Specification")
class ModelServiceSpec extends Specification {

    @Mock
    ModelRepository modelRepository

    @InjectMocks
    ModelService modelService

    static final Brand TOYOTA = Brand.builder().name("TOYOTA").build()

    Model toyotaModel1
    Model toyotaModel2
    ModelResponse toyotaResponse1
    ModelResponse toyotaResponse2

    def setup() {
        MockitoAnnotations.openMocks(this)

        toyotaModel1 = new Model(name: "Corolla", brand: TOYOTA)
        toyotaModel2 = new Model(name: "Camry", brand: TOYOTA)

        toyotaResponse1 = new ModelResponse(1L, "Corolla")
        toyotaResponse2 = new ModelResponse(2L, "Camry")
    }

    def "getModelResponseByBrandName should return models page by brand name"() {
        given: "A pageable response from the repository"
        Page<ModelResponse> modelPage = new PageImpl<>([
                new ModelResponse(1L, toyotaResponse1.name),
                new ModelResponse(2L, toyotaResponse2.name)
        ])

        given(modelRepository.findAllByBrandName("TOYOTA", PageRequest.of(0, 10)))
                .willReturn(modelPage)

        ModelRequestQuery modelRequestQuery = ModelRequestQuery.of("TOYOTA", 0, 10)

        when: "The service is called to retrieve the models"
        Page<ModelResponse> result = modelService.getModelResponseByBrandName(modelRequestQuery)

        then: "The result should not be empty and should contain the expected models"
        result.isNotEmpty()
        result.content.size() == 2
        result.content[0].name == "Corolla"
        result.content[1].name == "Camry"
    }

    def "getModelsByBrandName should return model names list by brand name"() {
        given: "A list of models from the repository"
        List<Model> models = [toyotaModel1, toyotaModel2]
        given(modelRepository.findAllByBrandName("TOYOTA")).willReturn(models)

        when: "The service is called to retrieve the model names"
        List<String> result = modelService.getModelsByBrandName("TOYOTA")

        then: "The result should contain the expected model names"
        result.size() == 2
        result.containsAll(["Corolla", "Camry"])
    }
}
