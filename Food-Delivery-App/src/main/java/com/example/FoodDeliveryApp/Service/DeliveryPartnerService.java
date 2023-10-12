package com.example.FoodDeliveryApp.Service;

import com.example.FoodDeliveryApp.Dto.Request.DeliveryPartnerRequest;
import com.example.FoodDeliveryApp.Model.DeliveryPartner;
import com.example.FoodDeliveryApp.Repositary.DeliveryPartnerRepo;
import com.example.FoodDeliveryApp.Transformer.PartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService {


    final DeliveryPartnerRepo deliveryPartnerRepo;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepo deliveryPartnerRepo) {
        this.deliveryPartnerRepo = deliveryPartnerRepo;
    }

    public String addPartner(DeliveryPartnerRequest deliveryPartnerRequest) {

        DeliveryPartner deliveryPartner = PartnerTransformer.PartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        DeliveryPartner savedPartner = deliveryPartnerRepo.save(deliveryPartner);

        return " you have been successfully registered!!!";
    }
}
