(defproject saber-rattler "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"conjars" "http://conjars.org/repo"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [incanter/incanter-core "1.5.2"]
                 [nz.ac.waikato.cms.weka/weka-dev "3.7.7"]
                 [nz.ac.waikato.cms.weka/LibSVM "1.0.5"] 
                 [enlive "1.1.4"]
                 [cascalog/cascalog-core "2.1.0"]
                 [cascalog-more-taps "0.3.0"]]
  :profiles {:provided {:dependencies [[org.apache.hadoop/hadoop-core "1.2.1"]]}})
