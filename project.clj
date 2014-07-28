(defproject saber-rattler "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"conjars" "http://conjars.org/repo"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [enlive "1.1.5"]
                 [cascalog/cascalog-core "2.1.0"]
                 [cascalog-more-taps "0.3.0"]]
  :profiles {:provided {:dependencies [[org.apache.hadoop/hadoop-core "1.2.1"]]}})
