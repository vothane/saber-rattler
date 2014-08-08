(ns saber-rattler.core
  (:use [saber-rattler.pitching-data]
        [cascalog.api]))

(def ^:dynamic *url* "http://www.brooksbaseball.net/tabs.php?player=")

(def pitchers
  #{{:name "Yu Darvish" :key 506433}
    {:name "Clayton Kershaw" :key 477132}
    {:name "Justin Verlander" :key 434378}
    {:name "Tim Lincecum" :key 453311}
    {:name "Jon Lester" :key 452657}
    {:name "Johnny Cueto" :key 456501}
    {:name "Clay Buchholz" :key 453329}
    {:name "Felix Hernandez" :key 433587}
    {:name "Adam Wainwright" :key 425794}
    {:name "David Price" :key 456034}
    {:name "Zack Greinke" :key 425844}})

(defn get-date [format]
  (.format (java.text.SimpleDateFormat. format) (.getTime (java.util.Calendar/getInstance))))

(defn get-key [name]
  (:key (first (clojure.set/select #(= (:name %) name) pitchers))))

(defn build-url [name metric]
  (str *url*
       (get-key name)
       "&p_hand=-1&ppos=-1&cn=200&gFilt=&time=month&minmax=ci&var="
       metric
       "&s_type=2&startDate=03/30/2007&endDate="
       (get-date "MM/dd/yyyy")
       "&balls=-1&strikes=-1&b_hand=-1"))

(defn make-queryable [coll]
   (vec (map vec coll)))

(defmacro query? [name & body]
  `(let [~'pitches  (remove '(fn [col] (= "Game" (first col))) (headers->categories (build-url ~name "gl")))
         ~'logs     (make-queryable (table->data (build-url ~name "gl")))
         ~'movement (make-queryable (table->data (build-url ~name "traj")))
         ~'outcomes (make-queryable (table->data (build-url ~name "po")))
         ~'metrics  (make-queryable (table->data (build-url ~name "so")))
         ~'averages (make-queryable (table->data (build-url ~name "ra")))]
    ~@body))

