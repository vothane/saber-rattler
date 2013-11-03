(ns cognition-ignition.pitching-data
  (:require [clojure.string :as string]
            [net.cgrand.enlive-html :as html])
  (:use [incanter.core])
  (:import [java.net URL]))

(defn to-keyword [input]
  (-> input
      string/lower-case
      (string/replace \space \-)
    keyword))

(defn get-headers [table]
  (->> (html/select table [:thead :tr :th])
       (map html/text)
       (map to-keyword)
    vec))

(defn get-rows [table]
  (->> (html/select table [:tr])
       (map #(html/select % [:td]))
       (map #(map html/text %))
    (filter seq)))

(defn load-data [url]
  (let [html    (html/html-resource (URL. url))
        table   (html/select html [:table.table])
        headers (get-headers table)
        rows    (remove (fn [col] (= "Game" (first col))) (get-rows table))]
    (dataset headers rows)))
