package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.filter

import android.os.Parcel
import android.os.Parcelable
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhoneFilterData

internal data class FilterDataImpl(
    override val brand: String,
    override val priceTop: Int,
    override val priceBottom: Int,
    override val sizeTop: Float,
    override val sizeBottom: Float
) : PhoneFilterData {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brand)
        parcel.writeInt(priceTop)
        parcel.writeInt(priceBottom)
        parcel.writeFloat(sizeTop)
        parcel.writeFloat(sizeBottom)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterDataImpl> {
        override fun createFromParcel(parcel: Parcel): FilterDataImpl {
            return FilterDataImpl(parcel)
        }

        override fun newArray(size: Int): Array<FilterDataImpl?> {
            return arrayOfNulls(size)
        }
    }
}