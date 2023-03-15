package strefakursow.electricity;

import java.util.Calendar;

public class ElectricityMeter {
    TariffProvider tp;

    private float kwh = 0;
    private int centsForKwh = 0;

    private boolean isTariffOn = false;
    private float kwhTariff = 0;
    private int centsForKwhTariff = 0;

    private int electricityTariffStartHour = 0;
    private int electricityTariffEndHour = 0;

    public ElectricityMeter() {
        tp = new TariffProvider() {
            @Override
            public boolean isTariffNow(){
                Calendar rightNow = Calendar.getInstance();
                int hour = rightNow.get(Calendar.HOUR_OF_DAY);
                return hour>electricityTariffStartHour && hour<electricityTariffEndHour;
            }
        };
    }

    public ElectricityMeter(TariffProvider tp) {
        this.tp = tp;
    }

    public void addKwh(float kwhToAdd){
        if(isTariffNow())
            kwhTariff+=kwhToAdd;
        else
            kwh+=kwhToAdd;
    }

    boolean isTariffNow() {
        return tp.isTariffNow();
    }

    /**
     *
     * @return how much more expensive is normal price comparing to tariff in percentages
     */
    public int getHowMoreExpensiveNormalIs(){
        return (centsForKwh*100/centsForKwhTariff) - 100;
    }

    public float getKwh() {
        return kwh;
    }

    public float getKwhTariff() {
        return kwhTariff;
    }

    void setCentsForKwh(int centsForKwh) {
        this.centsForKwh = centsForKwh;
    }

    void setTariffOn(boolean tariffOn) {
        isTariffOn = tariffOn;
    }

    void setCentsForKwhTariff(int centsForKwhTariff) {
        this.centsForKwhTariff = centsForKwhTariff;
    }

    void setElectricityTariffStartHour(int electricityTariffStartHour) {
        this.electricityTariffStartHour = electricityTariffStartHour;
    }

    void setElectricityTariffEndHour(int electricityTariffEndHour) {
        this.electricityTariffEndHour = electricityTariffEndHour;
    }

    public void reset() {
        kwh = 0;
        centsForKwh = 0;
        isTariffOn = false;
        kwhTariff = 0;
        centsForKwhTariff = 0;
        electricityTariffStartHour = 0;
        electricityTariffEndHour = 0;
    }
}
