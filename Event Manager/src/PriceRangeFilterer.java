/**
 * Price range filterer
 */
public class PriceRangeFilterer implements Filterer{
    double lowerBound;
    double upperBound;

    /**
     * Construstor
     * @param lowerBound lower bound of the price range
     * @param upperBound upper bound of the price range
     * @pre (lowerBound<=upperBound)
     */
    public PriceRangeFilterer(double lowerBound, double upperBound) {
        assert (lowerBound<=upperBound);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * @param concert
     * @return boolean whether its price is in that range
     */
    @Override
    public Boolean visitConcert(Concert concert) {
        assert concert!=null;
        if(concert.getPrice().isPresent()){
            double price=concert.getPrice().get().doubleValue();
            return Boolean.valueOf(price>=lowerBound&&price<=upperBound);
        }
        return Boolean.FALSE;
    }

    /**
     * @param workshop
     * @return boolean whether its price is in that range
     */
    @Override
    public Boolean visitWorkshop(Workshop workshop) {
        assert workshop!=null;
        if(workshop.getPrice().isPresent()){
            double price=workshop.getPrice().get().doubleValue();
            return Boolean.valueOf(price>=lowerBound&&price<=upperBound);
        }
        return Boolean.FALSE;
    }

    /**
     * @param gala
     * @return boolean whether its price is in that range
     */
    @Override
    public Boolean visitGala(Gala gala) {
        assert gala!=null;
        if(gala.getPrice().isPresent()){
            double price=gala.getPrice().get().doubleValue();
            return Boolean.valueOf(price>=lowerBound&&price<=upperBound);
        }
        return Boolean.FALSE;
    }

    /**
     * @param screening
     * @return boolean whether its price is in that range
     */
    @Override
    public Boolean visitScreening(Screening screening) {
        assert screening!=null;
        if(screening.getPrice().isPresent()){
            double price=screening.getPrice().get().doubleValue();
            return Boolean.valueOf(price>=lowerBound&&price<=upperBound);
        }
        return Boolean.FALSE;
    }

    /**
     * @param festival
     * @return boolean whether its price is in that range
     */
    @Override
    public Boolean visitFestival(Festival festival) {
        assert festival!=null;
        if(festival.getPrice().isPresent()){
            double price=festival.getPrice().get().doubleValue();
            return Boolean.valueOf(price>=lowerBound&&price<=upperBound);
        }
        return Boolean.FALSE;
    }
}
