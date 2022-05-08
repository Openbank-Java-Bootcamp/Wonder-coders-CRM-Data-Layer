package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.Contact;
import com.ironhack.WondercodersCRMDataLayer.Model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

}
