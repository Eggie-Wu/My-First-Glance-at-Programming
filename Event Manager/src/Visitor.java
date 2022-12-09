public interface Visitor<T> {
    T visitConcert(Concert concert);
    T visitWorkshop(Workshop workshop);
    T visitGala(Gala gala);
    T visitScreening(Screening screening);
    T visitFestival(Festival festival);
}
