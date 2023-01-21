package pl.alex.cars.extract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.alex.cars.car.body.Body;
import pl.alex.cars.car.body.BodyMapper;
import pl.alex.cars.car.body.BodyRepository;
import pl.alex.cars.car.brand.Brand;
import pl.alex.cars.car.brand.BrandMapper;
import pl.alex.cars.car.brand.BrandRepository;
import pl.alex.cars.car.chasis.Chasis;
import pl.alex.cars.car.chasis.ChasisMapper;
import pl.alex.cars.car.chasis.ChasisRepository;
import pl.alex.cars.car.engine.Engine;
import pl.alex.cars.car.engine.EngineMapper;
import pl.alex.cars.car.engine.EngineRepository;
import pl.alex.cars.car.file.Picture;
import pl.alex.cars.car.file.PictureRepository;
import pl.alex.cars.car.info.GeneralInfo;
import pl.alex.cars.car.info.GeneralInfoMapper;
import pl.alex.cars.car.info.GeneralInfoRepository;
import pl.alex.cars.car.model.Model;
import pl.alex.cars.car.model.ModelMapper;
import pl.alex.cars.car.model.ModelRepository;
import pl.alex.cars.car.modification.Modification;
import pl.alex.cars.car.modification.ModificationMapper;
import pl.alex.cars.car.modification.ModificationRepository;
import pl.alex.cars.car.running.RunningFeature;
import pl.alex.cars.car.running.RunningFeatureRepository;
import pl.alex.cars.car.running.RunningFeaturesMapper;
import pl.alex.cars.car.submodel.SubModel;
import pl.alex.cars.car.submodel.SubModelMapper;
import pl.alex.cars.car.submodel.SubModelRepository;
import pl.alex.cars.extract.dto.BodyExtractDto;
import pl.alex.cars.extract.dto.BrandExtractDto;
import pl.alex.cars.extract.dto.ChasisExtractDto;
import pl.alex.cars.extract.dto.EngineExtractDto;
import pl.alex.cars.extract.dto.GeneralInfoExtractDto;
import pl.alex.cars.extract.dto.ModelExtractDto;
import pl.alex.cars.extract.dto.ModificationExctractDto;
import pl.alex.cars.extract.dto.RunningFeatureExtractDto;
import pl.alex.cars.extract.dto.SubModelExtractDto;

@Component
public class ExtractUtils {

	Logger logger = LoggerFactory.getLogger(ExtractUtils.class);
	private final ModelRepository modelRepository;
	private final ModificationRepository modificationRepository;
	private final SubModelRepository subModelRepository;
	private final BrandRepository brandRepository;
	private final GeneralInfoRepository generalInfoRepository;
	private final BodyRepository bodyRepository;
	private final EngineRepository engineRepository;
	private final ChasisRepository chasisRepository;
	private final RunningFeatureRepository runningFeatureRepository;
	private final PictureRepository pictureRepository;
	
	public ExtractUtils(ModelRepository modelRepository, ModificationRepository modificationRepository,
			SubModelRepository subModelRepository, BrandRepository brandRepository, EngineRepository engineRepository,
			BodyRepository bodyRepository, ChasisRepository chasisRepository,
			RunningFeatureRepository runningFeatureRepository, GeneralInfoRepository generalInfoRepository,
			PictureRepository pictureRepository) {
		this.modelRepository = modelRepository;
		this.modificationRepository = modificationRepository;
		this.subModelRepository = subModelRepository;
		this.brandRepository = brandRepository;
		this.generalInfoRepository = generalInfoRepository;
		this.bodyRepository = bodyRepository;
		this.engineRepository = engineRepository;
		this.chasisRepository = chasisRepository;
		this.runningFeatureRepository = runningFeatureRepository;
		this.pictureRepository = pictureRepository;
	}

	@Value("${main.url}")
	private String mainUrl;

//	@Transactional
	public void extractAll() {
		logger.debug("[ExtractService] - extractAll");
		List<BrandExtractDto> dtos = null;
		try {
			long startTime = System.nanoTime();
			dtos = extractBrandData();
//			DtoPairs pair;
			for(BrandExtractDto brand : dtos) {
//				brand = extractData(brand).getBrand();
				Brand brandEntity = brandRepository.save(BrandMapper.INSTANCE.convertToEntity(brand));
				brand = extractBrandModelData(brand);
				for(ModelExtractDto model: brand.getModels()) {
					Model modelEntity = ModelMapper.INSTANCE.convertToEntity(model);
					modelEntity.setBrand(brandEntity);
					modelEntity = modelRepository.save(modelEntity);
					model = extractSubModelData(model);
//					model = extractData(model).getModel();
					if (model.getSubmodels() != null) {
						for (SubModelExtractDto subModel : model.getSubmodels()) {
							SubModel subModelEntity = SubModelMapper.INSTANCE.convertToEntity(subModel);
							subModelEntity.setModel(modelEntity);
							subModelEntity = subModelRepository.save(subModelEntity);
							subModel = extractModificationData(subModel);
							for (ModificationExctractDto modification : subModel.getModifications()) {
								Modification modificationEntity = ModificationMapper.INSTANCE.convertToEntity(modification);
								modificationEntity.setSubmodel(subModelEntity);
								modificationEntity = modificationRepository.save(modificationEntity);
								modification = fillSubModelModificationData(modification);
								String imageLink = modification.getImageLink();
								if(imageLink!=null) {
									Picture picture = extractPicture(imageLink);
									picture.setName(model.getName());
									picture.setModification(modificationEntity);
									picture = pictureRepository.save(picture);
									modificationEntity.setPicture(picture);
								}
								
								GeneralInfo generalInfo = GeneralInfoMapper.INSTANCE.convertToEntity(modification.getGeneralInfo());
								generalInfo = generalInfoRepository.save(generalInfo);
								Body body = BodyMapper.INSTANCE.convertToEntity(modification.getBody());
								body = bodyRepository.save(body);
								Engine engine = EngineMapper.INSTANCE.convertToEntity(modification.getEngine());
								engine = engineRepository.save(engine);
								Chasis chasis = ChasisMapper.INSTANCE.convertToEntity(modification.getChasis());
								chasis = chasisRepository.save(chasis);
								RunningFeature runningFeature = RunningFeaturesMapper.INSTANCE.convertToEntity(modification.getRunningFeature());
								runningFeature = runningFeatureRepository.save(runningFeature);
								
								modificationEntity.setBody(body);
								modificationEntity.setChasis(chasis);
								modificationEntity.setEngine(engine);
								modificationEntity.setGeneralInfo(generalInfo);
								modificationEntity.setRunningFeature(runningFeature);
								modificationRepository.save(modificationEntity);
							}
						}
					}
				}
			}
			long endTime = System.nanoTime();
			long milliseconds = (endTime - startTime) / 1000000;
			long duration = (milliseconds / 1000) % 60;
			System.out.println("Extraction Done! - " + duration + " seconds.");
			
		} catch (NullPointerException | MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

	private Picture extractPicture(String link) throws MalformedURLException {
		URL url = new URL(link);
		try (InputStream is = url.openStream()) {
			Picture picture = new Picture();
			picture.setData(is.readAllBytes());
			picture.setMainPicture(true);
			return picture;
		} catch (IOException e) {
			System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private SubModelExtractDto extractModificationData(SubModelExtractDto subModel) {
		logger.debug("[ExtractService] - extractSubmodelData");
		String line;
		String name = "";
		String link = "";
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(subModel.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				line = line.trim();
				if (line.trim().startsWith("<a href=\"/en/model/")) {
					link = line.substring(line.indexOf("\"") + 1, line.indexOf("\">"));
					if ((line = dis.readLine()) != null) {
						name = line.trim();
					}
					ModificationExctractDto modificationDto = ModificationExctractDto
							.builder()
							.name(name)
							.url(mainUrl + link)
							.build();
					if (subModel.getModifications() == null) {
						subModel.setModifications(new ArrayList<>());
					}
					subModel.getModifications().add(modificationDto);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return subModel;
	}

	private ModificationExctractDto fillSubModelModificationData(ModificationExctractDto dto) {
		logger.debug("[ExtractService] - extractSubmodelData");
		String line;
		String year = "";
		String brand =""; 
		String engine = dto.getName();
		String bodyType = "";
		String model = "";
		String doors = "";
		String seats = "";
		String length = "";
		String width = "";
		String height = "";
		String wheelBase = "";
		String weight = "";
		String maxWidth = "";
		String bootCapacity = "";
		String displacement = "";
		String kw = "";
		String hp = "";
		String torque = "";
		String fuelSupply = "";
		String cylinders = "";
		String cylinderDiameter = "";
		String valvesInCylinders = "";
		String gears = "";
		String fuel = "";
		String fuelCapacity = "";
		String ecoStandart = "";
		String abs = "";
		String frontBrakes = ""; 
		String rearBrakes = ""; 
		String tires = ""; 
		String wheels = "";
		String maxSpeed = "";
		String acceleration = "";
		String fuelTown = "";
		String fuelRoad = "";
		String fuelAverage = "";
		String imageLink = "";
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				
				line=line.trim();
				if(line.contains("style=\"background-image:")) {
					if(line.contains("/build/images/no_img.jpg")) {
						imageLink=null;
					} else {
						imageLink = line.substring(line.indexOf("(")+1, line.lastIndexOf(")"));
					}
				}
				// General Info.
				else if (lineIsEquals(line, "<td class=\"property\">Year</td>")) {
					line = dis.readLine().trim();
					year = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Brand</td>")) {
					line = dis.readLine().trim();
					brand = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Engine</td>")) {
					line = dis.readLine().trim();
					displacement = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Body type</td>")) {
					line = dis.readLine().trim();
					bodyType = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Model</td>")) {
					line = dis.readLine().trim();
					model = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Doors</td>")) {
					line = dis.readLine().trim();
					doors = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Seats</td>")) {
					line = dis.readLine().trim();
					seats = checkModificationData(line);
				}
				/* Body Features */
				else if (lineIsEquals(line, "<td class=\"property\">Length</td>")) {
					line = dis.readLine().trim();
					length = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Width</td>")) {
					line = dis.readLine().trim();
					width = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Height</td>")) {
					line = dis.readLine().trim();
					height = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Wheelbase</td>")) {
					line = dis.readLine().trim();
					wheelBase = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Weight</td>")) {
					line = dis.readLine().trim();
					weight = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Max width</td>")) {
					line = dis.readLine().trim();
					maxWidth = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Boot capacity</td>")) {
					line = dis.readLine().trim();
					bootCapacity = checkModificationData(line);
				}
				/* Engine Transmission */
				// TODO: make fuelSupply as Enum
				else if (lineIsEquals(line, "<td class=\"property\">Engine kw</td>")) {
					line = dis.readLine().trim();
					kw = checkModificationData(line);
				}  else if (lineIsEquals(line, "<td class=\"property\">Engine hp</td>")) {
					line = dis.readLine().trim();
					hp = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Torque</td>")) {
					line = dis.readLine().trim();
					torque = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Fuel supply</td>")) {
					line = dis.readLine().trim();
					fuelSupply = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Cylinders</td>")) {
					line = dis.readLine().trim();
					cylinders = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Cylinders diameter</td>")) {
					line = dis.readLine().trim();
					cylinderDiameter = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Valves in cylinders</td>")) {
					line = dis.readLine().trim();
					valvesInCylinders = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Gears</td>")) {
					line = dis.readLine().trim();
					gears = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Fuel</td>")) {
					line = dis.readLine().trim();
					fuel = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Fuel capacity</td>")) {
					line = dis.readLine().trim();
					fuelCapacity = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Eco standart</td>")) {
					line = dis.readLine().trim();
					ecoStandart = checkModificationData(line);
				}
				// Chasis
				else if (lineIsEquals(line, "<td class=\"property\">ABS</td>")) {
					line = dis.readLine().trim();
					abs = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Front brakes</td")) {
					line = dis.readLine().trim();
					frontBrakes = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Rear brakes</td>")) {
					line = dis.readLine().trim();
					rearBrakes = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Tires</td>")) {
					line = dis.readLine().trim();
					tires = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Wheels</td>")) {
					line = dis.readLine().trim();
					wheels = checkModificationData(line);
				}
				/* Running Features */
				else if (lineIsEquals(line, "<td class=\"property\">Max speed</td>")) {
					line = dis.readLine().trim();
					maxSpeed = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Acceleration</td>")) {
					line = dis.readLine().trim();
					acceleration = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Fuel town</td>")) {
					line = dis.readLine().trim();
					fuelTown = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Fuel road</td>")) {
					line = dis.readLine().trim();
					fuelRoad = checkModificationData(line);
				} else if (lineIsEquals(line, "<td class=\"property\">Fuel average</td>")) {
					line = dis.readLine().trim();
					fuelAverage = checkModificationData(line);
				}
			}
			dto.setImageLink(imageLink);
			// General Info.
			dto.setGeneralInfo(GeneralInfoExtractDto.builder()
					.year(year)
					.brand(brand)
					.engine(engine)
					.bodyType(bodyType)
					.model(model)
					.doors(doors)
					.seats(seats)
					.build());
			// Body Features
			dto.setBody(BodyExtractDto.builder()
					.length(length)
					.width(width)
					.height(height)
					.wheelBase(wheelBase)
					.weight(weight)
					.maxWidth(maxWidth)
					.bootCapacity(bootCapacity)
					.build());
			// Engine Transmission
			dto.setEngine(EngineExtractDto.builder()
					.displacement(displacement)
					.kw(kw)
				    .hp(hp)
				    .torque(torque)
				    .fuelSupply(fuelSupply)
				    .cylinders(cylinders)
				    .cylinderDiameter(cylinderDiameter)
				    .valvesInCylinders(valvesInCylinders)
				    .gears(gears)
				    .fuel(fuel)
				    .fuelCapacity(fuelCapacity)
				    .ecoStandart(ecoStandart)
					.build());
			// Chassis
			dto.setChasis(ChasisExtractDto.builder()
					.abs(abs)
					.frontBrakes(frontBrakes)
					.rearBrakes(rearBrakes)
					.tires(tires)
					.wheels(wheels)
					.build());
			// Running Features
			dto.setRunningFeature(RunningFeatureExtractDto.builder()
					.maxSpeed(maxSpeed)
					.acceleration(acceleration)
					.fuelTown(fuelTown)
					.fuelRoad(fuelRoad)
					.fuelAverage(fuelAverage)
					.build());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return dto;
	}

	private String checkModificationData(String line) {
		String substring = line.substring(line.indexOf(">") + 1, line.lastIndexOf("<"));
		if (substring.trim().equals("-")) {
			return null;
		} else {
			return substring;
		}
	}

	private boolean lineIsEquals(String line, String part) {
		return line.equals(part);
	}

	// TODO: check for NullPointer
	private DtoPairs extractData(Extractable dto) {
		logger.debug("[ExtractService] - extractData");
		String line;
		DtoPairs pair = null;
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				line = line.trim();
				if (line.trim().startsWith("<a title=\"")) {
					if (dto instanceof BrandExtractDto) {
						String name = line.substring(line.indexOf("\"") + 1, line.indexOf("\" href=\""));
						String link = line.substring(line.indexOf("/en/models/"), line.lastIndexOf("\""));
						System.out.println("[MODEL] - " + name);
						pair = new DtoPairs(fillBrandModelData((BrandExtractDto) dto, name, link), null);
					} else if (dto instanceof ModelExtractDto) {
						String link = line.substring(line.indexOf("/en/models/"), line.lastIndexOf("\""));
						String name = dis.readLine().trim();
						System.out.println("[SubModel] - " + name);
						pair = new DtoPairs(null, fillModelSubmodelData((ModelExtractDto) dto, name, link));
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return pair;
	}

	private BrandExtractDto extractBrandModelData(BrandExtractDto dto) {
		logger.debug("[ExtractService] - extractData");
		String line;
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				line = line.trim();
				if (line.trim().startsWith("<a title=\"")) {
					String name = line.substring(line.indexOf("\"") + 1, line.indexOf("\" href=\""));
					String link = line.substring(line.indexOf("/en/models/"), line.lastIndexOf("\""));
						System.out.println("[MODEL] - " + name);
						dto = fillBrandModelData(dto, name, link);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return dto;
	}
	
	private ModelExtractDto extractSubModelData(ModelExtractDto dto) {
		logger.debug("[ExtractService] - extractData");
		String line;
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(dto.getUrl()).openStream()))) {
			while ((line = dis.readLine()) != null) {
				line = line.trim();
				if (line.trim().startsWith("<a title=\"")) {
					String link = line.substring(line.indexOf("/en/models/"), line.lastIndexOf("\""));
					String name = dis.readLine().trim();
					System.out.println("[SubModel] - " + name);
					dto = fillModelSubmodelData(dto, name, link);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return dto;
	}

	private ModelExtractDto fillModelSubmodelData(ModelExtractDto dto, String name, String link) {
		SubModelExtractDto subModel = SubModelExtractDto
				.builder()
				.name(name)
				.url(mainUrl + link)
				.modifications(new ArrayList<>())
				.build();
		if (dto.getSubmodels() == null) {
			dto.setSubmodels(new ArrayList<>());
		}
		dto.getSubmodels().add(subModel);
		return dto;
	}

	private BrandExtractDto fillBrandModelData(BrandExtractDto dto, String name, String link) {
		ModelExtractDto modelDto = ModelExtractDto
				.builder()
				.name(name)
				.url(mainUrl + link)
				.submodels(null)
				.build();
		if (dto.getModels() == null) {
			dto.setModels(new ArrayList<>());
		}
		dto.getModels().add(modelDto);

		return dto;
	}

	private List<BrandExtractDto> extractBrandData() {
		logger.debug("[ExtractService] - extractManufacturersData");
		String line;
		List<BrandExtractDto> dtoList = new LinkedList<>();
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(new URL(mainUrl).openStream()))) {
			while ((line = dis.readLine()) != null) {
				if (line.trim().startsWith("<a href=\"/en/models/")) {
					String url = line.substring(line.indexOf("\"") + 1, line.indexOf(" class") - 1);
					String name = line.substring(line.indexOf(">") + 1, line.indexOf("<span"));
					BrandExtractDto dto = BrandExtractDto.builder().name(name)
							.url(mainUrl + url).build();
					dtoList.add(dto);
				}
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return dtoList;
	}

}
