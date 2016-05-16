package activitySelection;

import java.util.Arrays;

public class ActivitySelection {

	public static void main(String[] args) {
		
		Activity[] activities = new Activity[] {
				
				new Activity(1, 4),
				new Activity(3, 5),
				new Activity(0, 6),
				new Activity(5, 7),
				new Activity(3, 8),
				new Activity(5, 9),
				new Activity(6, 10),
				new Activity(8, 11),
				new Activity(8, 12),
				new Activity(2, 13),
				new Activity(12, 14),
		};

		Arrays.sort(activities, (a1, a2) -> Integer.compare(a1.getFinish(), a2.getFinish()));
		
		Activity lastSelectedActivity = activities[0];
        System.out.println(lastSelectedActivity);
        for (Activity activity : activities) {
        	
            if (activity.getStart() >= lastSelectedActivity.getFinish()) {
            	
                // Activities are compatible
            	System.out.println(activity);
                lastSelectedActivity = activity;
            }
        }
	}
	
	
	
}
