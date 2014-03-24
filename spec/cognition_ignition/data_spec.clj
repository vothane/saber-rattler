(ns cognition-ignition.data-spec
  (:use [speclj.core]
        [incanter.core]
        [cognition-ignition.pitching-data])
  (:import [weka.core Attribute]
           [weka.core Instance]
           [weka.core Instances]
           [weka.core FastVector]))

(describe "Datasets for Weka"

  (before-all
    (def home-away (FastVector. 2))
    (.addElement home-away "Home")
    (.addElement home-away "Away"))

  (it "home-away should have attributes"
    (should= 2 (.size home-away)))
   
)

(run-specs)