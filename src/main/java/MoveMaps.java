import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MoveMaps {

    public static Map<Integer,Set<Integer>> createLegalSimpleMoveMap() {
       Map<Integer,Set<Integer>> legalSimpleMoveMap = new HashMap<>();
       legalSimpleMoveMap.put(0, new HashSet<>(List.of(4,5)));
       legalSimpleMoveMap.put(1, new HashSet<>(List.of(5,6)));
       legalSimpleMoveMap.put(2, new HashSet<>(List.of(6,7)));
       legalSimpleMoveMap.put(3, new HashSet<>(List.of(7)));
       legalSimpleMoveMap.put(4, new HashSet<>(List.of(0,8)));
       legalSimpleMoveMap.put(5, new HashSet<>(List.of(0,1, 8,9)));
       legalSimpleMoveMap.put(6, new HashSet<>(List.of(1,2, 9,10)));
       legalSimpleMoveMap.put(7, new HashSet<>(List.of(2,3, 10,11)));
       legalSimpleMoveMap.put(8, new HashSet<>(List.of(4,5, 12,13)));
       legalSimpleMoveMap.put(9, new HashSet<>(List.of(5,6, 13,14)));
       legalSimpleMoveMap.put(10, new HashSet<>(List.of(6,7, 14,15)));
       legalSimpleMoveMap.put(11, new HashSet<>(List.of(7,15)));
       legalSimpleMoveMap.put(12, new HashSet<>(List.of(8,16)));
       legalSimpleMoveMap.put(13, new HashSet<>(List.of(8,9,16,17)));
       legalSimpleMoveMap.put(14, new HashSet<>(List.of(9,10,17,18)));
       legalSimpleMoveMap.put(15, new HashSet<>(List.of(10,11,18,19)));
       legalSimpleMoveMap.put(16, new HashSet<>(List.of(12,13,20,21)));
       legalSimpleMoveMap.put(17, new HashSet<>(List.of(13,14,21,22)));
       legalSimpleMoveMap.put(18, new HashSet<>(List.of(14,15,22,23)));
       legalSimpleMoveMap.put(19, new HashSet<>(List.of(15,23)));
       legalSimpleMoveMap.put(20, new HashSet<>(List.of(16,24)));
       legalSimpleMoveMap.put(21, new HashSet<>(List.of(16,17,24,25)));
       legalSimpleMoveMap.put(22, new HashSet<>(List.of(17,18,25,26)));
       legalSimpleMoveMap.put(23, new HashSet<>(List.of(18,19,26,27)));
       legalSimpleMoveMap.put(24, new HashSet<>(List.of(20,21,28,29)));
       legalSimpleMoveMap.put(25, new HashSet<>(List.of(21,22,29,30)));
       legalSimpleMoveMap.put(26, new HashSet<>(List.of(22,23,30,31)));
       legalSimpleMoveMap.put(27, new HashSet<>(List.of(23,31)));
       legalSimpleMoveMap.put(28, new HashSet<>(List.of(24)));
       legalSimpleMoveMap.put(29, new HashSet<>(List.of(24,25)));
       legalSimpleMoveMap.put(30, new HashSet<>(List.of(25,26)));
       legalSimpleMoveMap.put(31, new HashSet<>(List.of(26,27)));


       return legalSimpleMoveMap;
    }

    public static Map<Integer,Set<Integer>> createLegalJumpMoveMap() {
       Map<Integer,Set<Integer>> legalJumpMoveMap = new HashMap<>();
       legalJumpMoveMap.put(0, new HashSet<>(List.of(9)));
       legalJumpMoveMap.put(1, new HashSet<>(List.of(8,10)));
       legalJumpMoveMap.put(2, new HashSet<>(List.of(9,11)));
       legalJumpMoveMap.put(3, new HashSet<>(List.of(10)));
       legalJumpMoveMap.put(4, new HashSet<>(List.of(13)));
       legalJumpMoveMap.put(5, new HashSet<>(List.of(12,14)));
       legalJumpMoveMap.put(6, new HashSet<>(List.of(13,15)));
       legalJumpMoveMap.put(7, new HashSet<>(List.of(14)));
       legalJumpMoveMap.put(8, new HashSet<>(List.of(1,17)));
       legalJumpMoveMap.put(9, new HashSet<>(List.of(0,2,16,18)));
       legalJumpMoveMap.put(10, new HashSet<>(List.of(1,3,17,19)));
       legalJumpMoveMap.put(11, new HashSet<>(List.of(2,18)));
       legalJumpMoveMap.put(12, new HashSet<>(List.of(5,21)));
       legalJumpMoveMap.put(13, new HashSet<>(List.of(4,6,20,22)));
       legalJumpMoveMap.put(14, new HashSet<>(List.of(5,7,21,23)));
       legalJumpMoveMap.put(15, new HashSet<>(List.of(6,22)));
       legalJumpMoveMap.put(16, new HashSet<>(List.of(9,25)));
       legalJumpMoveMap.put(17, new HashSet<>(List.of(8,10,24,26)));
       legalJumpMoveMap.put(18, new HashSet<>(List.of(9,11,25,27)));
       legalJumpMoveMap.put(19, new HashSet<>(List.of(10,26)));
       legalJumpMoveMap.put(20, new HashSet<>(List.of(13,29)));
       legalJumpMoveMap.put(21, new HashSet<>(List.of(12,14,28,30)));
       legalJumpMoveMap.put(22, new HashSet<>(List.of(13,15,29,31)));
       legalJumpMoveMap.put(23, new HashSet<>(List.of(14,30)));
       legalJumpMoveMap.put(24, new HashSet<>(List.of(17)));
       legalJumpMoveMap.put(25, new HashSet<>(List.of(16,18)));
       legalJumpMoveMap.put(26, new HashSet<>(List.of(17,19)));
       legalJumpMoveMap.put(27, new HashSet<>(List.of(18)));
       legalJumpMoveMap.put(28, new HashSet<>(List.of(21)));
       legalJumpMoveMap.put(29, new HashSet<>(List.of(20,22)));
       legalJumpMoveMap.put(30, new HashSet<>(List.of(21,23)));
       legalJumpMoveMap.put(31, new HashSet<>(List.of(22)));


       return legalJumpMoveMap;
    }
// TODO: FIX values
    public static Map<Integer, Integer> createJumpOverIndexMap() {
        Map<Integer, Integer> jumpOverIndexMap = new HashMap<>();
        jumpOverIndexMap.put(9, 5);
        jumpOverIndexMap.put(11, 6);
        jumpOverIndexMap.put(13, 7);

        jumpOverIndexMap.put(17, 8);
        jumpOverIndexMap.put(19, 9);
        jumpOverIndexMap.put(21, 10);

        jumpOverIndexMap.put(25, 13);
        jumpOverIndexMap.put(27, 14);
        jumpOverIndexMap.put(29, 15);

        jumpOverIndexMap.put(33, 16);
        jumpOverIndexMap.put(35, 17);
        jumpOverIndexMap.put(37, 18);

        jumpOverIndexMap.put(41, 21);
        jumpOverIndexMap.put(43, 22);
        jumpOverIndexMap.put(45, 23);

        jumpOverIndexMap.put(49, 24);
        jumpOverIndexMap.put(51, 25);
        jumpOverIndexMap.put(53, 26);
        return jumpOverIndexMap;
    }
    
}
