package club.infinitymage.basicminigame.util;

public class CalculateGameID {

    public static String calculateGameID(Integer gameNum) {
        String[] gameIDs = {
                "alpha",
                "bravo",
                "charlie",
                "delta",
                "echo",
                "foxtrot",
                "golf",
                "hotel",
                "india",
                "juliett",
                "kilo",
                "lima",
                "mike",
                "november",
                "oscar",
                "papa",
                "quebec",
                "romeo",
                "sierra",
                "tango",
                "uniform",
                "victor",
                "whiskey",
                "xray",
                "yankee",
                "zulu"
        };

        return gameIDs[gameNum];

    }

}