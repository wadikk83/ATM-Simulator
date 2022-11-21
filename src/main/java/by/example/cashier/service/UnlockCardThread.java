package by.example.cashier.service;

import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;

import static java.time.LocalDateTime.now;

public class UnlockCardThread implements Runnable {
    BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();

    @Override
    public void run() {
        try {
            repository.getAll().stream()
                    .filter(e -> e.isBlocked() && (e.getBlockingDateTime().isBefore(now())))
                    .forEach(this::accept);


            //sleep 5 minutes
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            System.out.println("Thread for unlock card has been interrupted");
            ;
        }

    }

    private void accept(BankCardDto e) {
        e.setBlocked(false);
        repository.update(e);
    }
}
