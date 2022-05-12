package com.ironhack.WondercodersCRMDataLayer.Service;

import com.ironhack.WondercodersCRMDataLayer.Model.Contact;
import com.ironhack.WondercodersCRMDataLayer.Repository.ContactRepository;
import com.ironhack.WondercodersCRMDataLayer.classes.App;
import com.ironhack.WondercodersCRMDataLayer.classes.AppHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;



    public void showContacts() {
        List<Contact> contacts = contactRepository.findAll();
        String title = "CONTACT LIST";
        String[] headers = {"ID      ", "NAME                              ", "PHONE NUMBER                      ", "EMAIL                              "};
        List<String[]> list = new ArrayList<>();
        for (Contact contact : contacts) {
            String[] values = {Integer.toString(contact.getContactId()), contact.getName(), contact.getPhoneNumber(), contact.getEmail()};
            list.add(values);
        }
        AppHelp.printTable(title, headers, list);
    }

    public void showContact(Contact newContact) {
        String title = "CONTACT";
        String[] headers = {"ID      ", "NAME                              ", "PHONE NUMBER                      ", "EMAIL                              "};
        List<String[]> list = new ArrayList<>();
        String[] values = {Integer.toString(newContact.getContactId()), newContact.getName(), newContact.getPhoneNumber(), newContact.getEmail()};
        list.add(values);
        AppHelp.printTable(title, headers, list);
    }

    public void lookUpContact() {
        int id = Integer.parseInt(AppHelp.getId());
        if (contactRepository.findById(id).isPresent()) {
            showContact(contactRepository.findById(id).get());
        } else {
            System.err.println("No contact matches '" + id + "' --> Type 'show contacts' to see the list of available ids.");
        }
    }


}
