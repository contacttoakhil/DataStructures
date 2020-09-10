package main.java.onsite.arcesium;


import java.util.*;

public class FootballTeamSelection {
    static interface Criteria {
        public List<Player> meetCriteria(List<Player> persons);
    }

    static class CriteriaFit implements Criteria {
        @Override
        public List<Player> meetCriteria(List<Player> players) {
            List<Player> fitPlayers = new ArrayList<>();
            for (Player player : players) {
                if(player.getHeight() >= 5.8 && player.getBmi() <= 23.0){
                    fitPlayers.add(player);
                }
            }
            return fitPlayers;
        }
    }

    static class AndCriteria implements Criteria {

        private Criteria criteria;
        private Criteria otherCriteria;

        public AndCriteria(Criteria criteria, Criteria otherCriteria) {
            this.criteria = criteria;
            this.otherCriteria = otherCriteria;
        }

        @Override
        public List<Player> meetCriteria(List<Player> players) {
            List<Player> firstCriteriaPlayer = criteria.meetCriteria(players);
            return otherCriteria.meetCriteria(firstCriteriaPlayer);
        }
    }

    static class CriteriaStriker implements Criteria {
        @Override
        public List<Player> meetCriteria(List<Player> players) {
            List<Player> strikers = new ArrayList<Player>();
            for (Player player : players) {
                if(player.getScore() >= 50 ){
                    strikers.add(player);
                }
            }
            return strikers;
        }
    }

    static class CriteriaDefender implements Criteria {
        @Override
        public List<Player> meetCriteria(List<Player> players) {
            List<Player> defenders = new ArrayList<Player>();
            for (Player player : players) {
                if(player.getDefends() >= 30 ){
                    defenders.add(player);
                }
            }
            return defenders;
        }
    }

    static class Player {
        private String name;
        private Double height;
        private Double bmi;
        private Integer score;
        private Integer defends;

        public Player(String name, String height, String bmi, String score, String defends) {
            this.name = name;
            this.height = Double.parseDouble(height);
            this.bmi = Double.parseDouble(bmi);
            this.score = Integer.parseInt(score);
            this.defends = Integer.parseInt(defends);
        }

        public String getName() {
            return name;
        }

        public Double getHeight() {
            return height;
        }

        public Double getBmi() {
            return bmi;
        }

        public Integer getScore() {
            return score;
        }

        public Integer getDefends() {
            return defends;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", height=" + height +
                    ", bmi=" + bmi +
                    ", score=" + score +
                    ", defends=" + defends +
                    '}';
        }
    }

    public static List<List<String>> something(List<List<String>> input) {
        Criteria isFit = new CriteriaFit();
        Criteria isStriker = new CriteriaStriker();
        Criteria striker = new AndCriteria(isFit, isStriker);

        Criteria isDefender = new CriteriaDefender();
        Criteria defender = new AndCriteria(isFit, isDefender);

        List<Player> players = new ArrayList<>(input.size());
        for(List<String> row : input) {
            players.add(new Player(row.get(0),row.get(1),row.get(2),row.get(3),row.get(4)));
        }

        Comparator<Player> compareByScore = new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore().compareTo(o1.getScore());
            }
        };

        List<Player> strikers = striker.meetCriteria(players);
        Collections.sort(strikers, compareByScore);

        Comparator<Player> compareByDefends = new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getDefends().compareTo(o1.getDefends());
            }
        };
        List<Player> defenders = defender.meetCriteria(players);
        Collections.sort(defenders, compareByDefends);

        int min = Math.min(strikers.size(), defenders.size());
        strikers = strikers.subList(0, min);

        for(Player player :  defenders) {
            if(strikers.contains(player)) {
                defenders.remove(player);
            }
        }

        defenders = defenders.subList(0, min);

        List<List<String>> output = new ArrayList<>();

        for(Player player : players) {
            if(strikers.contains(player)) {
                output.add(Arrays.asList(player.name, "SELECT", "STRIKER"));
            }
            else if(defenders.contains(player)) {
                output.add(Arrays.asList(player.name, "SELECT", "DEFENDER"));
            }
            else output.add(Arrays.asList(player.name, "REJECT", "NA"));
        }
        return output;
    }

    public static void main(String[] args) {
        FootballTeamSelection temp1 = new FootballTeamSelection();
        List<List<String>> input = new ArrayList<>(4);

        // String name, String height, String bmi, String score, String defends
        // Stiker min goals 50, defender min defends 30
        /*List<String> one = new ArrayList<>();    // defender
        one.add("Boateng");
        one.add("6.1");
        one.add("22");
        one.add("24");
        one.add("45");
        input.add(one);

        List<String> two = new ArrayList<>();    // NA
        two.add("Kaka");
        two.add("6");
        two.add("22");
        two.add("1");
        two.add("1");
        input.add(two);

        List<String> three = new ArrayList<>();  // Striker
        three.add("Roanaldo");
        three.add("5.8");
        three.add("21");
        three.add("120");
        three.add("0");
        input.add(three);

        List<String> four = new ArrayList<>();   //Striker
        four.add("Suarez");
        four.add("5.9");
        four.add("20");
        four.add("100");
        four.add("1");
        input.add(four);*/

        List<String> one = new ArrayList<>();    // defender
        one.add("Boateng");
        one.add("6.1");
        one.add("22.0");
        one.add("24");
        one.add("45");
        input.add(one);

        List<String> two = new ArrayList<>();    // NA
        two.add("Kaka");
        two.add("6.0");
        two.add("22.0");
        two.add("1");
        two.add("1");
        input.add(two);

        List<String> three = new ArrayList<>();  // Striker
        three.add("RAMOS");
        three.add("6.3");
        three.add("22.0");
        three.add("67");
        three.add("70");
        input.add(three);

        List<List<String>> output = FootballTeamSelection.something(input);
        System.out.println(output);
    }
}
