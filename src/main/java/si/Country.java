package si;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private String country;
    private double standard_rate;

    @JsonDeserialize(using = NoFalse.class)
    private Double reduced_rate;

    @JsonDeserialize(using = NoFalse.class)
    private Double reduced_rate_alt;

    @JsonDeserialize(using = NoFalse.class)
    private Double super_reduced_rate;

    @JsonDeserialize(using = NoFalse.class)
    private Double parking_rate;

    public Country() {
    }

    public Country(String country, double standard_rate, Double reduced_rate) {
        this.country = country;
        this.standard_rate = standard_rate;
        this.reduced_rate = reduced_rate;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public double getStandard_rate() {
        return standard_rate;
    }
    public void setStandard_rate(double standard_rate) {
        this.standard_rate = standard_rate;
    }
    public Double getReduced_rate() {
        return reduced_rate;
    }
    public void setReduced_rate(Double reduced_rate) {
        this.reduced_rate = reduced_rate;
    }

    public Double getReduced_rate_alt() {
        return reduced_rate_alt;
    }

    public void setReduced_rate_alt(Double reduced_rate_alt) {
        this.reduced_rate_alt = reduced_rate_alt;
    }

    public Double getSuper_reduced_rate() {
        return super_reduced_rate;
    }

    public void setSuper_reduced_rate(Double super_reduced_rate) {
        this.super_reduced_rate = super_reduced_rate;
    }

    public Double getParking_rate() {
        return parking_rate;
    }

    public void setParking_rate(Double parking_rate) {
        this.parking_rate = parking_rate;
    }
}