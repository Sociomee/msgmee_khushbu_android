package com.inmortal.messenger.Adapter

/**
 * Created by Piyush Thapliyal
 */
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import com.inmortal.messenger.R
import com.inmortal.messenger.model.ContactModel


/**
 * Created by Piyush thapliyal
 */
class CustomAdapter(private val context: Context, private var contactModelArrayList: ArrayList<ContactModel>) : BaseAdapter() {



    private val colors = context.resources.getIntArray(R.array.myColors)
    override fun getViewTypeCount(): Int
    {
//        Original was count , now changed to 1 ,as to run app on tester's device.
        return if(count <1) {
            1
        } else {
            count
        }
    }

    override fun getItemViewType(position: Int): Int
    {

        return position
    }

    override fun getCount(): Int
    {
        return contactModelArrayList.size
    }

    override fun getItem(position: Int): Any
    {
        return contactModelArrayList[position]
    }

    override fun getItemId(position: Int): Long
    {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null)
        {
            holder = ViewHolder()
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.lv_item, null, true)

            holder.tvname = convertView!!.findViewById(R.id.name) as TextView
            holder.tvnumber = convertView.findViewById(R.id.number) as TextView
            holder.tvid = convertView.findViewById(R.id.id) as TextView
            holder.icon = convertView.findViewById(R.id.icon_bg) as RelativeLayout

            convertView.tag = holder
        }
        else
        {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.tvname!!.setText(contactModelArrayList[position].getNames())
        holder.tvnumber!!.setText(contactModelArrayList[position].getNumbers())
        holder.tvid!!.setText(contactModelArrayList[position].getIds())
        val shape:GradientDrawable = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.setColor(colors[(colors.indices).random()])
        holder.icon!!.setBackgroundColor(colors[(colors.indices).random()])
        holder.icon!!.background = shape

        return convertView
    }

    // below method is use for filtering data in our array list
    fun filterList(filterllist: ArrayList<ContactModel>) {
        // on below line we are passing filtered
        // array list in our original array list
        contactModelArrayList = filterllist
        notifyDataSetChanged()
    }
    private inner class ViewHolder
    {
        var tvname: TextView? = null
        var tvnumber: TextView? = null
        var tvid: TextView? = null
        var icon: RelativeLayout? = null
    }
}