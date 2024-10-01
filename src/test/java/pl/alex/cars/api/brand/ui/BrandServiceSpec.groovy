package pl.alex.cars.api.brand.ui

import pl.alex.cars.api.TestEntityFactory
import pl.alex.cars.api.brand.data.Brand
import pl.alex.cars.api.brand.data.BrandRepository
import pl.alex.cars.api.brand.exception.BrandNotFoundException
import spock.lang.Specification
import spock.lang.Title

@Title("BrandService Specification")
class BrandServiceSpec extends Specification {

    static final List<Brand> BRANDS = TestEntityFactory.createBrandsWithModels(5, 3)

    BrandRepository brandRepository = Mock()
    BrandService underTest = new BrandService(brandRepository)

    def "findAllBrands should return List<BrandResponse>"() {
        given: "a list of brands"
        brandRepository.findAll() >> BRANDS

        when: "finding all brands"
        List<BrandResponse> result = underTest.findAllBrands()

        then: "the result should have the correct size and names"
        result.size() == BRANDS.size()
        result*.name == BRANDS*.name
    }

    def "getBrandResponseByName should return BrandResponse with models"() {
        given: "a brand with models"
        Brand brand = BRANDS[0]
        String name = brand.getName()
        brandRepository.findByNameEquals(name) >> Optional.of(brand)

        when: "getting a brand response with models"
        BrandResponse response = underTest.getBrandResponseByName(name, true)

        then: "the response should contain models with correct names"
        !response.models.isEmpty()
        response.models.size() == brand.getModels().size()
        response.models*.name == brand.getModels()*.name
    }

    def "getBrandResponseByName should return BrandResponse without models"() {
        given: "a brand"
        Brand brand = BRANDS[0]
        String name = brand.getName()
        brandRepository.findByNameEquals(name) >> Optional.of(brand)

        when: "getting a brand response without models"
        BrandResponse response = underTest.getBrandResponseByName(name, false)

        then: "the response should not contain any models"
        response.models.isEmpty()
    }

    def "getBrandResponseByName should throw exception when brand is not found"() {
        given: "a non-existing brand name"
        String name = "NonExistingBrand"
        brandRepository.findByNameEquals(name) >> Optional.empty()

        when: "getting a brand response by wrong name"
        underTest.getBrandResponseByName(name, true)

        then: "a BrandNotFoundException should be thrown"
        def e = thrown(BrandNotFoundException)
        e.message.contains(name)
    }

    def "getMultipleBrands should return a list of BrandResponse"() {
        given: "a brand request with multiple names"
        BrandRequest brandRequest = new BrandRequest(names: BRANDS.take(2)*.name)
        brandRepository.findBrandByNameIn(brandRequest.getNames()) >> BRANDS.subList(0, 2)

        when: "getting multiple brands"
        List<BrandResponse> response = underTest.getMultipleBrands(brandRequest)

        then: "the result should contain the correct brands"
        response.size() == 2
        response*.name == BRANDS.take(2)*.name
    }
}
