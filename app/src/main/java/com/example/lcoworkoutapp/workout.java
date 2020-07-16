package com.example.lcoworkoutapp;

public class workout {
    String workoutname;
    String workoutduration;

    public void workout()
    {

    }
    public workout(String workoutname, String workoutduration) {
        this.workoutname = workoutname;
        this.workoutduration = workoutduration;
    }

    public String getWorkoutname() {
        return workoutname;
    }

    public void setWorkoutname(String workoutname) {
        this.workoutname = workoutname;
    }

    public String getWorkoutduration() {
        return workoutduration;
    }

    public void setWorkoutduration(String workoutduration) {
        this.workoutduration = workoutduration;
    }
}
