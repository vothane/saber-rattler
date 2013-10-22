(ns cognition-ignition.pitching-data-spec
  (:use [speclj.core]
        [incanter.core]
        [cognition-ignition.pitching-data]))

(def ^:dynamic *data-url* "http://www.brooksbaseball.net/tabs.php?player=452657&gFilt=&time=month&minmax=ci&var=gl&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")

(def pitch-data (load-data *data-url*))

(describe
  
  "The web table scraper"
  (it "should pull column names from the table columns."
    (should= ["Game" "Fourseam" "Sinker" "Change" "Curve" "Cutter"] (col-names pitch-data)))

  (it "should read eleven data rows."
    (should= 11 (nrow pitch-data)))

  (it "should read six columns."
    (should= 6 (ncol pitch-data))))

(run-specs)
