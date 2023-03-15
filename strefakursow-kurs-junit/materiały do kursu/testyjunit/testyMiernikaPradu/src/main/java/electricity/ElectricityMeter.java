package electricity;

//todo narzędzia ustawiającego taryfy i stawki

import java.util.Calendar;

public class ElectricityMeter {

    TariffProvider tp;

    private float kwh = 0;
    private int centsForKwh = 0;

    private boolean tariffOn = false;
    private float kwhTariff = 0;
    private int centsForKwhTariff = 0;

    private int electricityTariffStartHour = 0;
    private int electricityTariffEndHour = 0;

    public ElectricityMeter() {
        tp = new TariffProvider() {
            @Override
            public boolean isTariffNow() {
                Calendar rightNow = Calendar.getInstance();
                int hour = rightNow.get(Calendar.HOUR_OF_DAY);
                return hour > electricityTariffStartHour && hour < electricityTariffEndHour;
            }
        };
    }

    public ElectricityMeter(TariffProvider tp) {
        this.tp = tp;
    }

    public void addKwh(float kwhToAdd) {
        if (isTariffNow()) {
            kwhTariff += kwhToAdd;
        } else {
            kwh += kwhToAdd;
        }
    }

    public boolean isTariffNow() {
        return tp.isTariffNow();
    }

    /**
     * @return how much more expensive is normal price comparing to tariff in percentages
     */
    public int getHowMoreExpensiveNormalIs() {
        return (centsForKwh * 100 / centsForKwhTariff) - 100;
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
        this.tariffOn = tariffOn;
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
        this.kwh = 0;
        this.centsForKwh = 0;
        this.tariffOn = false;
        this.kwhTariff = 0;
        this.centsForKwhTariff = 0;
        this.electricityTariffStartHour = 0;
        this.electricityTariffEndHour = 0;
    }
}
