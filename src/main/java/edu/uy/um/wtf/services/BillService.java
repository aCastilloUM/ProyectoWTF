package edu.uy.um.wtf.services;


import ch.qos.logback.core.net.server.Client;
import edu.uy.um.wtf.entities.Bill;
import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.entities.Ticket;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill newBill(User client){
        if (client == null) {
            return null;
        }
        Bill bill = Bill.builder()
                .client(client)
                .snacks(new ArrayList<>())
                .tickets(new ArrayList<>())
                .totalSnacks(0)
                .totalTickets(0)
                .total(0)
                .build();
        return billRepository.save(bill);
    }

    public Bill addSnack(Bill bill, Snack snack, int cuantity){
        if (bill == null || snack == null) {
            return null;
        }
        bill.getSnacks().add(snack);
        bill.setTotalSnacks(bill.getTotalSnacks() + snack.getPrice());
        bill.setTotal(bill.getTotal() + snack.getPrice());
        return billRepository.save(bill);
    }

    public Bill addTicket(Bill bill, Ticket ticket){
        if (bill == null || ticket == null)
            return null;
        bill.getTickets().add(ticket);
        bill.setTotalTickets(bill.getTotalTickets() + ticket.getPrice());
        bill.setTotal(bill.getTotal() + ticket.getPrice());

        return billRepository.save(bill);
    }

    public Bill deleteTicket(Bill bill, Ticket ticket){
        if (bill == null || ticket == null)
            return null;
        bill.getTickets().remove(ticket);
        bill.setTotalTickets(bill.getTotalTickets() - ticket.getPrice());
        bill.setTotal(bill.getTotal() - ticket.getPrice());

        return billRepository.save(bill);
    }

    public Bill deleteSnack(Bill bill, Snack snack){
        if (bill == null || snack == null)
            return null;
        bill.getSnacks().remove(snack);
        bill.setTotalSnacks(bill.getTotalSnacks() - snack.getPrice());
        bill.setTotal(bill.getTotal() - snack.getPrice());

        return billRepository.save(bill);
    }

    public Bill findById(Long id){
        if (id == null)
            return null;
        return billRepository.findById(id).orElse(null);
    }

    public Bill updateBill(Bill bill){
        if (billRepository.existsById(bill.getId()))
            return billRepository.save(bill);
        return null;
    }

    public void deleteBill(Bill bill){
        billRepository.delete(bill);
    }

    public List<Bill> findByClient(User client){
        if (client == null)
            return null;
        return billRepository.findByClient(client);
    }




}
