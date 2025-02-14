(ns legacy.status-im.log-level.core
  (:require
    [legacy.status-im.multiaccounts.update.core :as multiaccounts.update]
    [re-frame.core :as re-frame]
    [utils.i18n :as i18n]
    [utils.re-frame :as rf]))

(rf/defn save-log-level
  {:events [:log-level.ui/change-log-level-confirmed]}
  [{:keys [db now] :as cofx} log-level]
  (let [old-log-level (get-in db [:profile/profile :log-level])]
    (when (not= old-log-level log-level)
      (multiaccounts.update/multiaccount-update
       cofx
       :log-level
       log-level
       {:on-success #(re-frame/dispatch [:profile/logout])}))))

(rf/defn show-change-log-level-confirmation
  {:events [:log-level.ui/log-level-selected]}
  [_ {:keys [name value]}]
  {:ui/show-confirmation
   {:title               (i18n/label :t/close-app-title)
    :content             (i18n/label :t/change-log-level
                                     {:log-level name})
    :confirm-button-text (i18n/label :t/close-app-button)
    :on-accept           #(re-frame/dispatch
                           [:log-level.ui/change-log-level-confirmed value])
    :on-cancel           nil}})
