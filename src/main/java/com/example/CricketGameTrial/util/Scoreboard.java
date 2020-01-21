package com.example.CricketGameTrial.util;

import com.example.CricketGameTrial.domain.FirstInnings;
import com.example.CricketGameTrial.domain.SecondInnings;

import java.text.DecimalFormat;

public class Scoreboard {
    public static String printScoreBoard(FirstInnings first, SecondInnings second) {

        java.lang.String newline = System.lineSeparator();
        StringBuilder res = new StringBuilder();
        int numOf_Overs = first.getOvers().size();
        java.lang.String overString = Integer.toString(numOf_Overs);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        if(first.getOvers().get(numOf_Overs-1).getBalls().size() != 6) overString = (numOf_Overs-1) + "."
                + first.getOvers().get(numOf_Overs-1).getBalls().size();

        res.append("<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>");
        res.append("<br>").append("First Innings Score Board").append("<br>").append(newline);

        res.append("<table> <caption>").append("Team ").append(first
                .getBattingTeam().getName()).append(" : ").append(first.getBattingTeam()
                .getRuns()).append("/").append(first.getBattingTeam().getWickets()).append(" (").append(overString)
                .append(")").append(" </caption> <tr> <th> Name </th>" +
                " <th> Runs-Scored </th> <th> Overs-Played </th> <th> Number of fours </th> <th> Number of sixes " +
                "</th> <th> Strike-rate </th> </tr>");

        for(int i=0;i < first.getBattingTeam().getPlayers().length; i++) {

            if(first.getBattingTeam().getPlayer(i).getBallsPlayed()!=0)
            first.getBattingTeam().getPlayer(i).setStrike_rate((double)first.getBattingTeam().getPlayer(i)
                    .getRunsScored()/first.getBattingTeam().getPlayer(i).getBallsPlayed()*100.0);

            res.append("<tr> <td>").append(first.getBattingTeam().getPlayer(i).getName()).append("</td> <td>")
                    .append(first.getBattingTeam().getPlayer(i).getRunsScored()).append("</td> <td>");

            java.lang.String v = Integer.toString(first.getBattingTeam().getPlayer(i).getBallsPlayed()/6);
            if(first.getBattingTeam().getPlayer(i).getBallsPlayed()%6 != 0) v += "."+(first.getBattingTeam()
                    .getPlayer(i).getBallsPlayed()%6);

            res.append(v).append("</td> <td>").append(first.getBattingTeam().getPlayer(i).getNumOfBoundaries())
                    .append("</td> <td>").append(first.getBattingTeam().getPlayer(i).getNumOfSixes()).append("</td>" +
                    "<td>").append(df.format(first.getBattingTeam().getPlayer(i).getStrike_rate()))
                    .append("</td> </tr>");
        }
        res.append("</table>").append("<br>").append("<br>");

        res.append("<table> <caption>").append("Team ").append(first
                .getBowlingTeam().getName()).append(" </caption> <tr> <th> Name </th>" +
                " <th> Overs-Played </th> <th> Runs-Given </th> <th> Wickets-taken </th> <th> Number of maidens " +
                "</th> <th> Economy </th></tr>");

        for(int i=6;i < first.getBowlingTeam().getPlayers().length; i++) {

            if(first.getBowlingTeam().getPlayer(i).getBallsBowled()!=0)
                first.getBowlingTeam().getPlayer(i).setEconomy((double)first.getBowlingTeam().getPlayer(i)
                        .getRunsGiven()/first.getBowlingTeam().getPlayer(i).getBallsBowled()*6.0);

            java.lang.String v = Integer.toString(first.getBowlingTeam().getPlayer(i).getBallsBowled()/6);
            if(first.getBowlingTeam().getPlayer(i).getBallsBowled()%6 != 0) v += "."+(first.getBowlingTeam()
                    .getPlayer(i).getBallsBowled()%6);

            res.append("<tr> <td>").append(first.getBowlingTeam().getPlayer(i).getName()).append("</td> <td>")
                    .append(v).append("</td> <td>").append(first.getBowlingTeam().getPlayer(i).getRunsGiven())
                    .append("</td> <td>").append(first.getBowlingTeam().getPlayer(i).getWicketsTaken())
                    .append("</td> <td>").append(first.getBowlingTeam().getPlayer(i).getNumOfMaidenOvers())
                    .append("</td> <td>").append(df.format(first.getBowlingTeam().getPlayer(i).getEconomy()))
                    .append(" </tr>");
        }
        res.append("</table>").append("<br>").append("<br>");

        numOf_Overs = second.getOvers().size();
        overString = Integer.toString(numOf_Overs);
        if(second.getOvers().get(numOf_Overs-1).getBalls().size() != 6) overString = (numOf_Overs-1)
                + "." + second.getOvers().get(numOf_Overs-1).getBalls().size();

        res.append("Second Innings Score Board").append("<br>").append(newline);

        res.append("<table> <caption>").append("Team ").append(second
                .getBattingTeam().getName()).append(" : ").append(second.getBattingTeam()
                .getRuns()).append("/").append(second.getBattingTeam().getWickets()).append(" (").append(overString)
                .append(")").append(" </caption> <tr> <th> Name </th>" +
                " <th> Runs-Scored </th> <th> Overs-Played </th> <th> Number of fours </th> <th> Number of sixes " +
                "</th> <th> Strike-rate </th> </tr>");

        for(int i=0;i < second.getBattingTeam().getPlayers().length; i++) {

            if(second.getBattingTeam().getPlayer(i).getBallsPlayed()!=0)
                second.getBattingTeam().getPlayer(i).setStrike_rate((double)second.getBattingTeam().getPlayer(i)
                        .getRunsScored()/second.getBattingTeam().getPlayer(i).getBallsPlayed()*100.0);

            res.append("<tr> <td>").append(second.getBattingTeam().getPlayer(i).getName()).append("</td> <td>")
                    .append(second.getBattingTeam().getPlayer(i).getRunsScored()).append("</td> <td>");

            java.lang.String v = Integer.toString(second.getBattingTeam().getPlayer(i).getBallsPlayed()/6);
            if(second.getBattingTeam().getPlayer(i).getBallsPlayed()%6 != 0) v += "."+(second.getBattingTeam()
                    .getPlayer(i).getBallsPlayed()%6);

            res.append(v).append("</td> <td>").append(second.getBattingTeam().getPlayer(i).getNumOfBoundaries())
                    .append("</td> <td>").append(second.getBattingTeam().getPlayer(i).getNumOfSixes()).append("</td>" +
                    "<td>").append(df.format(second.getBattingTeam().getPlayer(i).getStrike_rate()))
                    .append("</td> </tr>");
        }
        res.append("</table>").append("<br>").append("<br>");

        res.append("<table> <caption>").append("Team ").append(second
                .getBowlingTeam().getName()).append(" </caption> <tr> <th> Name </th>" +
                " <th> Overs-Played </th> <th> Runs-Given </th> <th> Wickets-taken </th> <th> Number of maidens " +
                "</th> <th> Economy </th> </tr>");

        for(int i=6;i < second.getBowlingTeam().getPlayers().length; i++) {

            if(second.getBowlingTeam().getPlayer(i).getBallsBowled()!=0)
                second.getBowlingTeam().getPlayer(i).setEconomy((double)second.getBowlingTeam().getPlayer(i)
                        .getRunsGiven()/second.getBowlingTeam().getPlayer(i).getBallsBowled()*6.0);

            java.lang.String v = Integer.toString(second.getBowlingTeam().getPlayer(i).getBallsBowled()/6);
            if(second.getBowlingTeam().getPlayer(i).getBallsBowled()%6 != 0) v += "."+(second.getBowlingTeam()
                    .getPlayer(i).getBallsBowled()%6);

            res.append("<tr> <td>").append(second.getBowlingTeam().getPlayer(i).getName()).append("</td> <td>")
                    .append(v).append("</td> <td>").append(second.getBowlingTeam().getPlayer(i).getRunsGiven())
                    .append("</td> <td>").append(second.getBowlingTeam().getPlayer(i).getWicketsTaken())
                    .append("</td> <td>").append(second.getBowlingTeam().getPlayer(i).getNumOfMaidenOvers())
                    .append("</td> <td>").append(df.format(second.getBowlingTeam().getPlayer(i).getEconomy()))
                    .append("</td> </tr>");
        }
        res.append("</table>").append("<br>").append("<br>");

        // After second innings if batting team runs are greater than bowling team runs then batting team wins
        res.append("<b>");
        if(second.getBattingTeam().getRuns() > second.getBowlingTeam().getRuns()) {
            res.append("Team ").append(second.getBattingTeam().getName()).append(" wins against Team ")
                    .append(second.getBowlingTeam().getName()).append(" by ").append((10-second.getBattingTeam()
                    .getWickets())).append(" wickets!").append("<br>").append(newline);
        } else if(second.getBattingTeam().getRuns() < second.getBowlingTeam().getRuns()) {
            res.append("Team ").append(second.getBowlingTeam().getName()).append(" wins against Team ")
                    .append(second.getBattingTeam().getName()).append(" by ").append((second.getBowlingTeam().getRuns()
                    - second.getBattingTeam().getRuns())).append(" runs!").append("<br>").append(newline);
        } else {
            res.append("Match between Team ").append(second.getBattingTeam().getName()).append(" and Team ")
                    .append(second.getBowlingTeam().getName()).append(" is tied!").append("<br>").append(newline);
        }
        res.append("</b>");
        return res.toString();
    }
}
