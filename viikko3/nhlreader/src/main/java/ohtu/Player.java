
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String nationality;
    private int goals;

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }
    private int assists;
    
    
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        int teho = goals + assists;
        return name +" " + nationality +" teho " +goals +"+" +assists +"=" +teho ;
    }
    
    
    @Override
    public int compareTo(Player p){
        int tama = this.assists + this.goals;
        int toinen = p.getAssists() + p.getGoals();
        
        return toinen - tama;
    }
}
