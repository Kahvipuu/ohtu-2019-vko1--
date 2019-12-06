package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

class QueryBuilder {

    Matcher m;

    //Onko tässä jotenkin väärä lähtökohta???
    public QueryBuilder() {
        this.m = new All();
    }
    
    public Matcher build() {
        return m;
    }

    //Jotain vikaa siinä että muokkaan this.m:ää ?? jotenkin tulisi säilyttää se ehjänä??
    public QueryBuilder playsIn(String team){
        this.m = new And(m, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int i, String category){
        this.m = new And(m, new HasAtLeast(i, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int i, String category){
        this.m = new And(m, new HasFewerThan(i, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2){
        this.m = new Or(m1, m2);
        return this;
    }
    
}
