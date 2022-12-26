package pl.alex.cars.extract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.alex.cars.extract.dto.BodyExtractDto;
import pl.alex.cars.extract.dto.BrandExtractDto;
import pl.alex.cars.extract.dto.ChasisExtractDto;
import pl.alex.cars.extract.dto.EngineExtractDto;
import pl.alex.cars.extract.dto.GeneralInfoExtractDto;
import pl.alex.cars.extract.dto.ModelExtractDto;
import pl.alex.cars.extract.dto.ModificationExctractDto;
import pl.alex.cars.extract.dto.RunningFeatureExtractDto;
import pl.alex.cars.extract.dto.SubModelExtractDto;

public class ExtractUtils {

	Logger logger = LoggerFactory.getLogger(ExtractUtils.class);

	private String mainUrl = "https://car-info.com";

	public List<BrandExtractDto> extractAll() {
		logger.debug("[ExtractService] - extractAll");
		List<BrandExtractDto> dtos = null;
		try {
			long startTime = System.nanoTime();
			dtos = extractBrandData();
//			DtoPairs pair;
			for(BrandExtractDto brand : dtos) {
//				brand = extractData(brand).getBrand();
				brand = extractBrandModelData(brand);
				for(ModelExtractDto model: brand.getModels()) {
					model = extractSubModelData(model);
//					model = extractData(model).getModel();
					if (model.getSubmodels() != null) {
						for (SubModelExtractDto subModel : model.getSubmodels()) {
							subModel = extractModificationData(subModel);
							for (ModificationExctractDto modification : subModel.getModifications()) {
								modification = fillSubModelModificationData(modification);
							}
						}
					}
				}
			}
			long endTime = System.nanoTime();
			long milliseconds = (endTime - startTime) / 1000000;
			long duration = (milliseconds / 1000) % 60;
			System.out.println("Extraction Done! - " + duration);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return dtos;
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
					imageLink = line; //TODO: fix image link
				}
				// General Info.
				else if (startWith(line, "<td class=\"property\">Year</td>")) {
					line = dis.readLine().trim();
					year = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Brand</td>")) {
					line = dis.readLine().trim();
					brand = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Engine</td>")) {
					line = dis.readLine().trim();
					displacement = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Body type</td>")) {
					line = dis.readLine().trim();
					bodyType = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Model</td>")) {
					line = dis.readLine().trim();
					model = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Doors</td>")) {
					line = dis.readLine().trim();
					doors = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Seats</td>")) {
					line = dis.readLine().trim();
					doors = checkModificationData(line);
				}
				// Body Features
				else if (startWith(line, "<td class=\"property\">Length</td>")) {
					line = dis.readLine().trim();
					length = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Width</td>")) {
					line = dis.readLine().trim();
					width = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Height</td>")) {
					line = dis.readLine().trim();
					height = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Wheelbase</td>")) {
					line = dis.readLine().trim();
					wheelBase = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Weight</td>")) {
					line = dis.readLine().trim();
					weight = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Max width</td>")) {
					line = dis.readLine().trim();
					maxWidth = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Boot capacity</td>")) {
					line = dis.readLine().trim();
					bootCapacity = checkModificationData(line);
				}
				// Engine Transmission
				else if (startWith(line, "<td class=\"property\">Engine kw</td>")) {
					line = dis.readLine().trim();
					kw = checkModificationData(line);
				}  else if (startWith(line, "<td class=\"property\">Engine hp</td>")) {
					line = dis.readLine().trim();
					hp = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Torque</td>")) {
					line = dis.readLine().trim();
					torque = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Fuel supply</td>")) {
					line = dis.readLine().trim();
					fuelSupply = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Cylinders</td>>")) {
					line = dis.readLine().trim();
					cylinders = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Cylinders diameter</td>")) {
					line = dis.readLine().trim();
					cylinderDiameter = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Valves in cylinders</td>")) {
					line = dis.readLine().trim();
					valvesInCylinders = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Gears</td>")) {
					line = dis.readLine().trim();
					gears = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Fuel</td>")) {
					line = dis.readLine().trim();
					fuel = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Fuel capacity</td>")) {
					line = dis.readLine().trim();
					fuelCapacity = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Eco standart</td>")) {
					line = dis.readLine().trim();
					ecoStandart = checkModificationData(line);
				}
				// Chasis
				else if (startWith(line, "<td class=\"property\">ABS</td>")) {
					line = dis.readLine().trim();
					abs = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Front brakes</td")) {
					line = dis.readLine().trim();
					frontBrakes = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Rear brakes</td>")) {
					line = dis.readLine().trim();
					rearBrakes = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Tires</td>")) {
					line = dis.readLine().trim();
					tires = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Wheels</td>")) {
					line = dis.readLine().trim();
					wheels = checkModificationData(line);
				}
				// Running features
				else if (startWith(line, "<td class=\"property\">Max speed</td>")) {
					line = dis.readLine().trim();
					maxSpeed = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Acceleration</td>")) {
					line = dis.readLine().trim();
					acceleration = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Fuel town</td>")) {
					line = dis.readLine().trim();
					fuelTown = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Fuel road</td>")) {
					line = dis.readLine().trim();
					fuelRoad = checkModificationData(line);
				} else if (startWith(line, "<td class=\"property\">Fuel average</td>")) {
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
				    .ValvesInCylinders(valvesInCylinders)
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
		return line.substring(line.indexOf(">") + 1, line.lastIndexOf("<"));
	}

	private boolean startWith(String line, String part) {
		return line.startsWith(part);
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
