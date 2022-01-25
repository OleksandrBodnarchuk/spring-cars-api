package pl.alex.cars;

public class Modification {
    private String name;
    private String pictureLink;
    private String engine;
    private String power;
    private String fuel;
    private String body;
    private String yearOfProduction;

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Modification: {" + '\n' +
                "Name: " + name + '\n' +
                "Picture: " + pictureLink + '\n' +
                "Production years: " + yearOfProduction + '\n' +
                "engine='" + engine + '\n' +
                "power='" + power + '\n' +
                "fuel='" + fuel + '\n' +
                "body='" + body + '\n' +
                "}";
    }
}
