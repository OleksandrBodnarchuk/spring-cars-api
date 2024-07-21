package pl.alex.cars.api;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pl.alex.cars.api.brand.entity.Brand;
import pl.alex.cars.api.model.entity.Model;

public class TestEntityFactory {

  public static Brand createBrand(Long id, String name) {
    return Brand.builder()
        .id(id)
        .name(name)
        .models(new ArrayList<>())
        .build();
  }

  public static Model createModel(Long id, String name, Brand brand) {
    Model model = Model.builder()
        .id(id)
        .name(name)
        .build();
    if (brand != null) {
      model.setBrand(brand);
      brand.getModels().add(model);
    }
    return model;
  }

  public static List<Brand> createBrands(Integer count) {
    if (Objects.isNull(count)){
      count = 10;
    }
    List<Brand> brands = new ArrayList<>();
    for (int i = 1; i <= count; i++) {
      brands.add(createBrand((long) i, "Brand" + i));
    }
    return brands;
  }

  public static List<Model> createModels(Integer count, Brand brand) {
    if (Objects.isNull(count)){
      count = 10;
    }
    List<Model> models = new ArrayList<>();
    for (int i = 1; i <= count; i++) {
      models.add(createModel((long) i, "Model" + i, brand));
    }
    return models;
  }

  public static List<Brand> createBrandsWithModels(int brandCount, int modelCountPerBrand) {
    List<Brand> brands = new ArrayList<>();
    for (int i = 1; i <= brandCount; i++) {
      Brand brand = createBrand((long) i, "Brand" + i);
      createModels(modelCountPerBrand, brand);
      brands.add(brand);
    }
    return brands;
  }
}
