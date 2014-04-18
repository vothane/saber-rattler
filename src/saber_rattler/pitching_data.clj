(ns saber-rattler.pitching-data
  (:require [clojure.string :as string]
            [net.cgrand.enlive-html :as html])
  (:use [incanter.core])
  (:import [java.net URL]))

(defn headers->categories [url]
  (let [html  (html/html-resource (URL. url))
        table (html/select html [:table.table])
        data  (->> (html/select table [:thead :tr :th])
                   (map html/text))]
    (rest data)))

(defn table->data [url]
  (let [html  (html/html-resource (URL. url))
        table (html/select html [:table.table])
        raw   (->> (html/select table [:tr])
                   (map #(html/select % [:td]))
                   (map #(map html/text %)))]
    (filter seq raw)))

(defn- group-by-category [categories data]
  (let [category  (first data)
        stat-data (rest data)
        groupings (partition 2 2 (interleave categories stat-data))]
    (map #(cons category %) groupings)))

(defn data-converter [ids data]
  (let [categorizer (partial group-by-category ids)]       
    (map categorizer data)))
