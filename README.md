# Data-Binding-Simple-Example
Reduce recycler view adapter boiler plate code with data binding 

```
abstract class MyBaseAdapter : RecyclerView.Adapter<MyBaseAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        /*Used layout id as View Type */
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        bindData(position,holder.binding)
    }

    override fun getItemViewType(position: Int): Int {
        /*Used layout id as View Type */
        return getLayoutIdByPosition(position)
    }

    abstract fun bindData(position:Int,binding: ViewDataBinding)

    abstract fun getLayoutIdByPosition(position: Int): Int

      class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
```


```
class MyAdapter<T>(var layoutId:Int, var data:List<T>) : MyBaseAdapter(){
    var commonLayoutBRVariable=BR.adapterData

    private var onItemClickListener:OnItemClickListener<T>?=null

    fun setOnItemClickListener(onItemClickListener:OnItemClickListener<T>?){
        this.onItemClickListener=onItemClickListener
    }

    fun setLayoutBrVariable(varName:Int){
        this.commonLayoutBRVariable=varName
    }

    override fun bindData(position: Int, binding: ViewDataBinding) {
        binding.setVariable(commonLayoutBRVariable,data[position])
        binding.root.setOnClickListener { this.onItemClickListener?.onItemClicked(data[position],position) }
        binding.executePendingBindings()
    }

    override fun getLayoutIdByPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
```


```
 @BindingAdapter(value = ["onClickHandler","recyclerViewAdapter"],requireAll = false)
    @JvmStatic
    fun <T> RecyclerView.setRecyclerViewAdapter(listener: OnItemClickListener<T>?,list: List<T>?){
        val adapter = MyAdapter(R.layout.item_adapter, list?: arrayListOf())
        adapter.setOnItemClickListener(listener)
        this.adapter = adapter
    }
```

```
  <androidx.recyclerview.widget.RecyclerView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            tools:listitem="@layout/item_adapter"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            onClickHandler="@{listener}"
            recyclerViewAdapter="@{list}" />
```            
