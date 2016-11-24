using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class MenuController : MonoBehaviour {

	// Use this for initialization
	public Canvas intro;
	public Canvas main;


	public Button pressStartBtn;
	void Start () {
		intro.gameObject.SetActive(true);
		main.gameObject.SetActive(false);
		pressStartBtn.onClick.AddListener(()=>GoMenu());
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void GoMenu()
	{
		pressStartBtn.onClick.RemoveAllListeners();
		intro.gameObject.SetActive(false);
		main.gameObject.SetActive(true);
	}
}
