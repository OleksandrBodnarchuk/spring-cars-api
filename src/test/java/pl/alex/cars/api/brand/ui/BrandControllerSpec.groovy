package pl.alex.cars.api.brand.ui


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static pl.alex.cars.api.TestEntityFactory.createBrands
import static pl.alex.cars.api.TestEntityFactory.createBrandsWithModels

@WebMvcTest(BrandController)
class BrandControllerSpec extends Specification {

    private BrandController underTest
    private BrandService brandService
    private MessageSource messageSource

    def setup() {
        brandService = Stub(BrandService.class)
        messageSource = Stub(MessageSource.class)
        underTest = new BrandController(messageSource, brandService)
    }

    def "POST /brands/multiple should return multiple brands"() {
        given: "A valid BrandRequest with brand names"
        def brandRequest = new BrandRequest(names: ["BrandA", "BrandB"])
        def expectedResponse = [createBrandsWithModels(1,2)]

        // Mock the service call
        brandService.getMultipleBrands(brandRequest) >> expectedResponse

        when: "Calling the controller method"
        def response = underTest.getMultipleBrands(brandRequest)

        then: "It returns 200 OK and the correct response body"
        response.statusCode == HttpStatus.OK
        response.body == expectedResponse
    }

    def "POST /brands/multiple should return 400 when brand names are empty"() {
        given: "An empty BrandRequest"
        def brandRequest = new BrandRequest(names: [])

        // Mock the message source for validation error message
        messageSource.getMessage("brand.name.list.rest.validation", null, Locale.getDefault()) >> "Brand names cannot be empty"

        when: "Calling the controller method"
        def response = underTest.getMultipleBrands(brandRequest)

        then: "It returns 400 Bad Request with the appropriate error message"
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body == "Brand names cannot be empty"
    }

    def "GET /brands/{name} should return brand by name"() {
        given: "A valid brand name"
        String brandName = "Toyota"
        def expectedBrandResponse = new BrandResponse(1, "Toyota", List.of())

        // Mock the service call
        brandService.getBrandResponseByName(brandName, true) >> expectedBrandResponse

        when: "Calling the controller method"
        def response = underTest.getBrandByName(brandName, true)

        then: "It returns 200 OK and the correct brand response"
        response.statusCode == HttpStatus.OK
        response.body == expectedBrandResponse
    }

    def "GET /brands should return all brands"() {
        given: "A list of BrandResponses"
        def expectedResponses = createBrands(10)

        // Mock the service call
        brandService.findAllBrands() >> expectedResponses

        when: "Calling the controller method"
        def response = underTest.getBrands()

        then: "It returns 200 OK and the correct list of brands"
        response.statusCode == HttpStatus.OK
        response.body == expectedResponses
    }
}
