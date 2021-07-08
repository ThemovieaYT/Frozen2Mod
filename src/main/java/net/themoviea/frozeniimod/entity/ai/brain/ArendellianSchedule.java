package net.themoviea.frozeniimod.entity.ai.brain;

import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Schedule;
import net.themoviea.frozeniimod.Frozen2Mod;

public class ArendellianSchedule extends Schedule
{
	public static final Schedule QUEEN;
	public static final Schedule CITIZEN;
	public static final Schedule CITIZEN_CHILDREN;
	
	public static void registerArendellianSchedule() {
		
	}
	
	static 
	{
		QUEEN = Schedule.register("frozeniimod:queen").withActivity(0, Activity.IDLE).build();
		CITIZEN = Schedule.register("frozeniimod:citizen").withActivity(10, Frozen2Mod.HOME_ROUTINE).withActivity(2000, Activity.WORK).withActivity(11000, Frozen2Mod.HOME_ROUTINE).withActivity(12000, Activity.REST).build();
		CITIZEN_CHILDREN = Schedule.register("frozeniimod:citizen_children").withActivity(10, Frozen2Mod.KID_HOME_ROUTINE).withActivity(2000, Frozen2Mod.SCHOOL).withActivity(5000, Activity.PLAY).withActivity(9000, Frozen2Mod.KID_HOME_ROUTINE).withActivity(12000, Activity.REST).build();
	}
}
