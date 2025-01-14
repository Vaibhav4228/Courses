package Practice;

public class City {
    private String name;
    private String country;
    private long population;

    public City(String name, String country, long population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    
    

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
       if(name == null || name.isEmpty()) {
           throw new IllegalArgumentException("Name cannot be null or empty");
       }
        this.name = name;
    }

    /**
     * @return String return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
       if(name == null || name.isEmpty()) {
           throw new IllegalArgumentException("Country cannot be null ");
       }
        this.country = country;
    }

    /**
     * @return long return the population
     */
    public long getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(long population) {
        if(population <= 0) {
            throw new IllegalArgumentException("Population cannot be less than or equal to 0");
        }
        this.population = population;
    }
    public class CityTest{
        public static void main(String[] args){
            City city = new City("Hyderabad", "India", 1000000);
        }
    }
    
}


