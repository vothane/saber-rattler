(ns saber-rattler.pitching-data
  (:require [clojure.string :as string]
            [net.cgrand.enlive-html :as html])
  (:use [incanter.core])
  (:import [java.net URL]))

(defn get-game-logs [url]
  (let [html    (html/html-resource (URL. url))
        table   (html/select html [:table.table])
        data    (->> (html/select table [:tr])
                     (map #(html/select % [:td]))
                     (map #(map html/text %))
                     (filter seq))]
    data))

(defn- pitch-per-type [pitch-types pitches]
  (let [game            (first pitches)
        pitches-of-game (rest pitches)
        group-by-pitch  (for [pitch-type pitch-types num-pitches pitches-of-game] 
                          (vector game pitch-type num-pitches))]
    group-by-pitch))

(defn game-log-converter [game-log pitch-types]
  (let [pitch-categorizer (partial pitch-per-type pitch-types)]       
    (into [] (map pitch-categorizer game-log))))
