package my.edu.tarc.contact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import my.edu.tarc.contact.databinding.FragmentFirstBinding
import my.edu.tarc.contact.viewmodel.ContactViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("First Fragment", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)



        val contactAdapter = ContactAdapter()

        contactViewModel.contactList.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                Toast.makeText(context, getString(R.string.no_record), Toast.LENGTH_SHORT).show()
            }
            else{
                contactAdapter.setContact(it)
            }
        }

        //Connect adapter to the RecyclerView
        binding.recyclerViewContact.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.recyclerViewContact.adapter = contactAdapter

    }

    override fun onDestroyView() {
        Log.d("First Fragment", "onDestroyView")
        super.onDestroyView()



        _binding = null
    }
}