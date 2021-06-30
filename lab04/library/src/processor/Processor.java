package processor;

public interface Processor {

    /**
     * Metoda służąca do zlecania zadań
     *
     * @param task - tekst podlegający przetwarzaniu (reprezentuje zadanie)
     * @param sl   - słuchacz, który będzie informowany o statusie przetwarzania
     * @return - wartość logiczna mowiąca o tym, czy zadanie przyjęto do
     *         przetwarzania (nie można zlecić kolejnych zadań dopóki bierzące
     *         zadanie się nie zakończyło i nie został pobrany wynik przetwarzania
     */
    boolean submitTask(String task, StatusListener sl);

    /**
     * Metoda służąca do pobierania informacji o algorytmie przetwarzania (np. "Algorytm zlcizający samogłoski")
     *
     * @return - informacja o algorytmie przetwarzania
     */
    String getInfo();

    /**
     * Metoda służąca do pobierania wyniku przetwarzania
     *
     * @return - tekst reprezentujący wynik przetwarzania
     */
    String getResult();
}
