package com.mirego.trikot.analytics

import android.content.Context
import com.mixpanel.android.mpmetrics.MixpanelAPI
import org.json.JSONObject

class MixpanelAnalyticsService(
    context: Context,
    mixpanelToken: String,
    optOutTrackingDefault: Boolean = false
) : AnalyticsService {
    private val mixpanelAnalytics =
        MixpanelAPI.getInstance(context, mixpanelToken, optOutTrackingDefault)

    override val name: String = "MixpanelAnalytics"

    override fun disableAnalyticsCollection() {
        mixpanelAnalytics.optOutTracking()
    }

    override fun enableAnalyticsCollection() {
        mixpanelAnalytics.optInTracking()
    }

    override fun identifyUser(userId: String, properties: AnalyticsPropertiesType) {
        mixpanelAnalytics.identify(userId)
        mixpanelAnalytics.people.identify(userId);
        mixpanelAnalytics.people.set(properties.asJSONProperties())

        mixpanelAnalytics
    }

    override fun incrementUserProperties(incrementalProperties: AnalyticsIncrementalProperties) {
        mixpanelAnalytics.people.increment(incrementalProperties)
    }

    override fun logout() {
        mixpanelAnalytics.reset()
    }

    override fun setSuperProperties(properties: AnalyticsPropertiesType) {
        mixpanelAnalytics.registerSuperProperties(properties.asJSONProperties())
    }

    override fun setUserProperties(properties: AnalyticsPropertiesType) {
        TODO("Not yet implemented")
    }

    override fun unsetAllSuperProperties() {
        mixpanelAnalytics.clearSuperProperties()
    }

    override fun trackEvent(event: AnalyticsEvent, properties: AnalyticsPropertiesType) {
        mixpanelAnalytics.track(event.name, properties.asJSONProperties())
    }

    override fun unsetSuperProperties(propertyNames: List<String>) {
        propertyNames.forEach { name ->
            mixpanelAnalytics.unregisterSuperProperty(name)
        }
    }

    private fun AnalyticsPropertiesType.asJSONProperties(): JSONObject {

        return JSONObject().also { props ->
            forEach { (type, value) ->
                props.put(type, value)
            }
        }
    }
}
