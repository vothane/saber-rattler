(ns saber-rattler.core-test
  (:require [clojure.test :refer :all]
            [saber-rattler.core :refer :all]))

(query? "Yu Darvish"
 (is (< 0 (count logs))))