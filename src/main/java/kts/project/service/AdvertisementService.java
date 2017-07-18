package kts.project.service;

import kts.project.controller.dto.AddAdvertisementDTO;
import kts.project.model.Advertisement;
import kts.project.model.enumerations.AdvertisementType;
import kts.project.model.enumerations.Currency;
import kts.project.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 14-Jul-17.
 */

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    public Advertisement findById(Long id){
        return advertisementRepository.findById(id);
    }

    public List<Advertisement> findAll(){ return advertisementRepository.findAll();}

    public void save(Advertisement a){advertisementRepository.save(a);}

    public void delete(Advertisement a){advertisementRepository.delete(a);}

    public void delete(Long id){advertisementRepository.delete(id);}

    public boolean checkAdvertisementDTOInput(AddAdvertisementDTO addAdvertisementDTO){
        if (
                addAdvertisementDTO.getEndingDate() == null ||
                        addAdvertisementDTO.getPhoneNumber().equals("") ||
                        addAdvertisementDTO.getTitle().equals("") ||
                        addAdvertisementDTO.getType() == null ||
                        addAdvertisementDTO.getPrice() < 0){
            return false;
        }
        else{

            return true;
        }
    }

    public AdvertisementType getAdvertisementFromString(String stringType){
        if(stringType == null){
            System.out.println("tip je null");
            return null;
        }
        if(stringType.equalsIgnoreCase("RENT")){
            System.out.println("tip je rent");
            return AdvertisementType.RENT;
        }
        else if(stringType.equalsIgnoreCase("SELL")){
            System.out.println("tip je sell");
            return AdvertisementType.SELL;
        }
        return null;
    }

    public Currency getCurrencyFromString(String stringCurrency){
        if(stringCurrency == null){
            System.out.println("Currency je null");
            return null;
        }
        if(stringCurrency.equalsIgnoreCase("EUR")){
            System.out.println("Currency je EUR");
            return Currency.EUR;
        }
        else if(stringCurrency.equalsIgnoreCase("RSD")){
            System.out.println("Currency je RSD");
            return Currency.RSD;
        }
        else if(stringCurrency.equalsIgnoreCase("USD")){
            System.out.println("Currency je USD");
            return Currency.USD;
        }
        return null;
    }

    public List<Advertisement> typeFilter(List<Advertisement> list, AdvertisementType type){
        List<Advertisement> copy = new ArrayList<Advertisement>();
        for(Advertisement a : list){ copy.add(a);}

        for (Advertisement a : copy){

            if(type != null && type != a.getType()){

                list.remove(a);
            }
        }
        return list;
    }

    public List<Advertisement> priceFilter(List<Advertisement> list, double minPrice, double maxPrice, Currency currency){

        List<Advertisement> copy = new ArrayList<>();
        for(Advertisement a : list){ copy.add(a);}

        for (Advertisement a : copy){

            if(currency != a.getCurrency()){

                //ako je maxPrice == 0.0 onda znaci da nije nista upisano u input polje
                if(maxPrice > 0){

                    if(a.getPrice() < minPrice || a.getPrice() > maxPrice){
                        list.remove(a);
                    }

                }
                // ako nije popunuo maxPrice polje, treba proveriti samo minPrice
                else if(maxPrice == 0.0 ){
                    if(a.getPrice() < minPrice){
                        list.remove(a);
                    }
                }

            }

        }
        return list;
    }
}
