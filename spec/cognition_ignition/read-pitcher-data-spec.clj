(ns cognition-ignition.read-pitcher-data-spec
  (:use [speclj.core :exclude [before after]]
        [incanter.core]
        [cognition-ignition.pitching-data]))

(def data-url "http://www.brooksbaseball.net/tabs.php?player=452657&gFilt=&time=month&minmax=ci&var=gl&s_type=2&startDate=03/30/2007&endDate=10/20/2013&balls=-1&strikes=-1&b_hand=-1")

(def ds (load-data data-url))
