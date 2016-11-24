using UnityEngine;
using System.Collections;
using UnityEngine.EventSystems;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Canvas2Controller : MonoBehaviour, IPointerEnterHandler {

	// Use this for initialization
	public Texture banner;
	public RawImage bannerDisplay;
	public GameObject efects;

	private AudioSource [] sources;

	void Start () {
		sources = new AudioSource[2];
		sources = efects.GetComponents<AudioSource>();
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetMouseButtonDown(0))
        {
            SceneManager.LoadScene("game");
        }
    }

	public void OnPointerEnter(PointerEventData evenData)
	{
		bannerDisplay.GetComponent<RawImage>().texture = banner;
		sources[1].Play();
        
	}
}
