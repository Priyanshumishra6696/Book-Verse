package com.example.booksapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.api.Constants
import com.example.booksapp.api.NetworkResponse
import com.example.booksapp.api.RetrofitInstance
import com.example.booksapp.db.StoreBooks.BooksEntity
import com.example.booksapp.db.getDatabaseInstance
import com.example.booksapp.models.apiModels.Item
import com.example.booksapp.models.apiModels.SearchResponse
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    //INPUT TEXT HANDLING
    private val _inputTextForBookSearch = mutableStateOf("Books")
    fun getInputTextForBookSearch() : String {
        return _inputTextForBookSearch.value
    }
    fun changeInputTextForBookSearch(inputText : String){
        _inputTextForBookSearch.value = inputText
    }


    //API calling handling
    private val GoogleBooksApi = RetrofitInstance.googleBooksApi
    private val _booksResult = MutableLiveData<NetworkResponse<SearchResponse>>()
    val booksResult : MutableLiveData<NetworkResponse<SearchResponse>> = _booksResult
    fun getData(inputText: String){
        _booksResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = GoogleBooksApi.getBooks(inputText)//,Constants.apikey
                if(response.isSuccessful){
                    response.body()?.let {
                        _booksResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _booksResult.value = NetworkResponse.Error("failed to load data")
                }
            }catch(e : Exception){
                _booksResult.value = NetworkResponse.Error("failed to load data")
            }
        }
    }
    private val _scienceBooksResult = MutableLiveData<NetworkResponse<SearchResponse>>()
    val scienceBooksResult : MutableLiveData<NetworkResponse<SearchResponse>> = _scienceBooksResult
    fun getScienceBooks(){
        _scienceBooksResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try{
                val response = GoogleBooksApi.getBooks(inputText = "subject:Science")//,Constants.apikey
                if(response.isSuccessful){
                    response.body()?.let {
                        _scienceBooksResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _scienceBooksResult.value = NetworkResponse.Error("failed to load data")
                }
            }catch (e:Exception){
                _scienceBooksResult.value = NetworkResponse.Error("failed to load data")
            }
        }
    }

    //managing multiple sections
    private val _bookResultsMap = mutableMapOf<String, MutableLiveData<NetworkResponse<SearchResponse>>>()

    fun getBooksForSection(section: String): LiveData<NetworkResponse<SearchResponse>> {
        if (!_bookResultsMap.containsKey(section)) {
            _bookResultsMap[section] = MutableLiveData<NetworkResponse<SearchResponse>>()
        }
        return _bookResultsMap[section]!!
    }

    fun fetchBooks(section: String) {
        val liveData = getBooksForSection(section) as MutableLiveData
        liveData.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = GoogleBooksApi.getBooks(inputText = "subject:$section")//, Constants.apikey
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveData.value = NetworkResponse.Success(it)
                    }
                } else {
                    liveData.value = NetworkResponse.Error("Failed to load data for $section")
                }
            } catch (e: Exception) {
                liveData.value = NetworkResponse.Error("Failed to load data for $section")
            }
        }
    }

    // room db
    val booksDao = getDatabaseInstance.booksDatabase.getBooksDao()
    val savedBooksList : LiveData<List<BooksEntity>> = booksDao.getSavedBooks()

    fun addBook(item:Item){
        viewModelScope.launch {
            booksDao.addBook(
                booksEntity = BooksEntity(
                    booksname = item.volumeInfo.title,//if(!=null){}else{"NA"}
                    link = if(item.volumeInfo.imageLinks!=null){item.volumeInfo.imageLinks.thumbnail}else{""},
                    authors = if(item.volumeInfo.authors[0]!=null){item.volumeInfo.authors[0]}else{"NA"},
                    averageRating = if(item.volumeInfo.averageRating!=null){item.volumeInfo.averageRating.toString()}else{"NA"},
                    uniqueId = item.id,
                    description = if(item.volumeInfo.description!=null){item.volumeInfo.description}else{"NA"},
                    pageCount = if(item.volumeInfo.pageCount!=null){item.volumeInfo.pageCount}else{0},
                    language = if(item.volumeInfo.language!=null){item.volumeInfo.language}else{"NA"},
                    maturityRating = if(item.volumeInfo.maturityRating!=null){item.volumeInfo.maturityRating}else{"NA"},
                    category = if(item.volumeInfo.categories!=null){item.volumeInfo.categories[0]}else{"NA"},
                    publisher = if(item.volumeInfo.publisher!=null){item.volumeInfo.publisher}else{"NA"},
                    publishedDate = if(item.volumeInfo.publishedDate!=null){item.volumeInfo.publishedDate}else{"NA"},
                    printType = if(item.volumeInfo.printType!=null){item.volumeInfo.printType}else{"NA"},
                    contentVersion = if(item.volumeInfo.contentVersion!=null){item.volumeInfo.contentVersion}else{"NA"},
                    infolink = if(item.volumeInfo.infoLink!=null){item.volumeInfo.infoLink}else{""}
                )
            )
        }


    }

    fun deleteBook(id : Int){
        viewModelScope.launch {
            booksDao.deleteBook(id)
        }
    }


    //handling book screen
    var currItem : Item ? by mutableStateOf(null)
    fun updatecurrItem(item: Item){
        currItem=item
    }
    fun getcurrItem() : Item? {
        return currItem
    }

    //handling bookmark icon

    fun checkIfBookisSaved(item: Item) : Boolean{
        var bookIsSaved by mutableStateOf(false)
        viewModelScope.launch {
            if(booksDao.searchBookById(item.id)!=null){
                bookIsSaved = true
            }
        }
        return bookIsSaved
    }

    //managing popup
    var showPopUp by mutableStateOf(false)
    var message by mutableStateOf("")
}
