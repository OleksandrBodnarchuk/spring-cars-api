package pl.alex.cars.api.model.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import pl.alex.cars.api.model.dto.ModelResponse
import pl.alex.cars.api.model.service.ModelService
import spock.lang.Specification
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ModelControllerSpec extends Specification {

    private ModelController modelController
    private ModelService modelService = Mock()

    def setup() {
        modelController = new ModelController(modelService)
    }

    def "GET /models/{brand} should return a page of models for the given brand"() {
        given: "A brand and a page request"
        String brandName = "BrandA"
        Integer page = 0
        Integer size = 10

        and: "Mocked modelService returns a page of models"
        def modelResponses = [new ModelResponse(1l,"Model1"), new ModelResponse(2l,"Model2")]
        def modelPage = new PageImpl<>(modelResponses)
        modelService.getModelResponseByBrandName(_) >> modelPage

        when: "Calling the controller method"
        ResponseEntity<Page<ModelResponse>> response = modelController.getModelsByBrand(brandName, page, size)

        then: "The response should be 200 OK with the models in the response body"
        response.statusCode == HttpStatus.OK
        response.body.content.size() == 2
        response.body.content[0].name == "Model1"
        response.body.content[1].name == "Model2"
    }

    def "GET /models/{name}/modifications should return 301 Moved Permanently with redirection header"() {
        given: "A model name"
        String modelName = "Model1"

        when: "Calling the controller method"
        ResponseEntity<?> response = modelController.sendRedirrect(modelName)

        then: "The response should be 301 Moved Permanently with the location header"
        response.statusCode == HttpStatus.MOVED_PERMANENTLY
        response.headers.get(HttpHeaders.LOCATION).contains("/submodels/Model1")
    }
}
