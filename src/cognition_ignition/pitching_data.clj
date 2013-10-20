(ns cognition-ignition.pitching-data
  (:require [clojure.string :as string]
            [net.cgrand.enlive-html :as html])
  (:use [incanter.core])
  (:import [java.net URL]))

(defn load-data
  [url]
  (let [html    (html/html-resource (URL. url))
        table   (html/select html [:table.table])
        headers (->> (html/select table [:thead :tr :th])
                     (map html/text)
                     vec)
        rows    (->> (html/select table [:tbody :tr])
                     (map #(html/select % [:td]))
                     (map #(map html/text %))
                     (filter seq))]
  (dataset headers rows)))
