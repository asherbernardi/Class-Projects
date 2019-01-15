package c15p6;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class PartyPlanner {

    

    /**
     * Determine an invitation set with maximum total conviviality rating
     * without including both a person and that person's immediate supervisor
     * or subordinate
     * @param personnel A forest of personnel trees
     * @return A set of personnel trees, each entry being a person to invite
     */
    public static HashSet<PersonnelTree> makeGuestList(PersonnelTree[] personnel) {
    	int n = personnel.length;
    	// The max conviviality of the a party containing all
    	// personnel rooted at this node. C[0] is the answer.
    	int[] C = new int[n];
    	// The path used for construction. Do we take this
    	// level of hierarchy in this branch or not?
    	boolean[] P = new boolean[n];
    	// initialization
    	for (int i = 0; i < n; i++) {
    		// Only mark the leafs of the hierarchy tree
    		if (!personnel[i].hasSubordinates()) {
    			C[i] = personnel[i].conviviality();
    			// this marks the personnel as processed and
    			// lets it know where it is in the array
    			personnel[i].aux = new Integer(i);
    			P[i] = true;
    		}
    	}
    	// finishing
    	assert personnel[0].aux == null;
    	while (personnel[0].aux == null) {
    		for (int i = 0; i < n; i++) {
    			if (personnel[i].hasSubordinates() &&
    					personnel[i].aux == null) {
    				int childrensConv = 0;
    				int grandChildrensConv = 0;
        			for (PersonnelTree child: personnel[i].subordinates()) {
        				// the subordinates of this personnel
        				// haven't been processed yet
        				if (child.aux == null)
        					break;
        				else {
        					childrensConv += C[(Integer) child.aux];
        					for (PersonnelTree grandChild: child.subordinates()) {
        						if (grandChild.aux == null)
                					break;
                				else {
                					grandChildrensConv += C[(Integer) grandChild.aux];
                				}
        					}
        				}
        			}
        			// is it better to invite this personnel or his/her
        			// subordinates?
        			if (personnel[i].conviviality() + grandChildrensConv >
        					childrensConv) {
        				C[i] = personnel[i].conviviality() + grandChildrensConv;
        				P[i] = true;
        			}
        			else {
        				C[i] = childrensConv;
        				P[i] = false;
        			}
        			personnel[i].aux = new Integer(i);
        		}
    		}
    	}
    	// reconstruct the answer.
    	HashSet<PersonnelTree> inviteList = new HashSet<PersonnelTree>();
    	reconstructAnswer(personnel[0], inviteList, P);
    	
        return inviteList;
    }
    
    private static void reconstructAnswer(PersonnelTree personnel, HashSet<PersonnelTree> inviteList, boolean[] P) {
    	if (P[(Integer) personnel.aux]) {
    		inviteList.add(personnel);
    		for (PersonnelTree child: personnel.subordinates()) {
    			for (PersonnelTree grandChild: child.subordinates()) {
    				reconstructAnswer(grandChild, inviteList, P);
    			}
    		}
    	}
    	else {
    		for (PersonnelTree child: personnel.subordinates()) {
    			reconstructAnswer(child, inviteList, P);
    		}
    	}
    }
    
}
