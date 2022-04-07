package ru.job4j.ood;

/**
 * В методе выполняются проверки, данная валидация может через время измениться,
 *  лучше условие передавать через предикат
 */
public class OrderGenerator {
    public void process(Order order){

        RepositoryData repository = new RepositoryData();
        ConfirmationEmailSender mailSender = new ConfirmationEmailSender();

        if (order.isValid() && repository.save(order)) {
            mailSender.sendConfirmationEmail(order);
        }
    }
}
