using UnityEngine;
using System.Collections;
using UnityEngine.UI;


public class Canvas1Controller : MonoBehaviour {

	public Text pressStartTxt;

	private Color oldColor;

	private float cont;

	// Use this for initialization
	void Start () {
		cont = 0;
		oldColor = new Color(pressStartTxt.color.r,pressStartTxt.color.g,pressStartTxt.color.b);
	}

	
	// Update is called once per frame
	void Update () {
		cont += Time.deltaTime;

		if(cont>=1)
		{
			if(pressStartTxt.color.a ==1f)
				pressStartTxt.color = new Color(oldColor.r,oldColor.g,oldColor.b,0);
			else
				pressStartTxt.color = new Color(oldColor.r,oldColor.g,oldColor.b,1);

			cont = 0;
		}
	}
}
