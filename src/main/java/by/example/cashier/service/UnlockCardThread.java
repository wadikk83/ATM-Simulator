package by.example.cashier.service;

import by.example.cashier.config.ApplicationConfig;
import by.example.cashier.model.dto.BankCardDto;
import by.example.cashier.repository.BankCardRepositoryDTO;
import lombok.extern.slf4j.Slf4j;

import static java.time.LocalDateTime.now;

@Slf4j
public class UnlockCardThread implements Runnable {
    BankCardRepositoryDTO repository = ApplicationConfig.getBankCardRepository();

    @Override
    public void run() {
        try {
            repository.getAll().stream()
                    .filter(e -> e.isBlocked() && (e.getBlockingDateTime().isAfter(now())))
                    .forEach(this::restoreAccess);

            //sleep 5 minutes
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            System.out.println("Thread for unlock card has been interrupted");
        }
    }

    private void restoreAccess(BankCardDto e) {
        e.setBlocked(false);
        repository.update(e);
        log.info("Bank card has been unlocked -> " + e.getCardNumber());
    }
}
