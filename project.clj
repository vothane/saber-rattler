(defproject cognition-ignition "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [incanter/incanter-core "1.5.2"]
                 [nz.ac.waikato.cms.weka/weka-dev "3.7.7"]
                 [nz.ac.waikato.cms.weka/LibSVM "1.0.5"] 
                 [enlive "1.1.4"]]
  :profiles {:dev {:dependencies [[speclj "2.5.0"]]}}
  :plugins [[speclj "2.5.0"]]
  :test-paths ["spec"])